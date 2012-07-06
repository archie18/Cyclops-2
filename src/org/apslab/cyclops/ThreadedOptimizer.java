/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apslab.cyclops;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Genetic algorithm implementation
 * @author andreas
 */
public class ThreadedOptimizer {
    private int elitism;
    private IPopulationInitializer populationInitializer;
    private ThreadedBreeder breeder;
    private ITerminationCriterion terminationCriterion;
    private ExecutorService threadPool;
    private int numberOfThreads;
    private IGeneticOperator crossOverOperator;
    private Mutation mutationOperator;
    private ISelector selector;
    private IFitnessFunction fitnessFunction;

    private static Map<Double, Integer> selected;

    public ThreadedOptimizer setPopulationInitializer(IPopulationInitializer populationInitializer) {
        this.populationInitializer = populationInitializer;
        return this;
    }

    public ThreadedOptimizer setElitism(int elitism) {
        this.elitism = elitism;
        return this;
    }

    public ThreadedOptimizer setCrossOverOperator(IGeneticOperator crossOverOperator) {
        this.crossOverOperator = crossOverOperator;
        return this;
    }

    public ThreadedOptimizer setMutationOperator(Mutation mutationOperator) {
        this.mutationOperator = mutationOperator;
        return this;
    }

    public ThreadedOptimizer setSelector(ISelector selector) {
        this.selector = selector;
        return this;
    }

    public ThreadedOptimizer setFitnessFunction(IFitnessFunction fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
        return this;
    }

    public ThreadedOptimizer setTerminationCriterion(ITerminationCriterion terminationCriterion) {
        this.terminationCriterion = terminationCriterion;
        return this;
    }

    public ThreadedOptimizer setNumberOfThreads(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
        return this;
    }

    static synchronized void addParents(List<Individual> parents) {
        for (Individual parent : parents) {
            if (selected.get(parent.getFitness().getDoubleValue()) == null) {
                selected.put(parent.getFitness().getDoubleValue(), 1);
            } else {
                selected.put(parent.getFitness().getDoubleValue(), selected.get(parent.getFitness().getDoubleValue()) + 1);
            }
        }
    }

    public void optimize() {
        Individual generationTop;
        Individual overallTop;

        // Initialize population
        List<Individual> population = populationInitializer.getPopulation();

        // Evaluate initial population
        for (Individual individual : population) {
            individual = fitnessFunction.calculate(individual);
            //System.out.println(individual);
        }

        // Initialize thread pool
        threadPool = Executors.newFixedThreadPool(numberOfThreads);
        BlockingQueue<List<Individual>> jobQueue = new ArrayBlockingQueue<List<Individual>>(population.size());
        BlockingQueue<Individual> offspringQueue = new ArrayBlockingQueue<Individual>(population.size());
        for (int i = 0; i < numberOfThreads; i++) {
            threadPool.execute(new ThreadedBreeder().setCrossOverOperator(crossOverOperator).setMutationOperator(mutationOperator).setSelector(selector).setFitnessFunction(fitnessFunction).setJobQueue(jobQueue).setOffspringQueue(offspringQueue));
        }

        // Logging
        List<Individual> populationCpy = new ArrayList<Individual>(population);
        Collections.sort(populationCpy);
        generationTop = populationCpy.get(0);
        overallTop = populationCpy.get(0);

        // Generation loop
        int iteration = 0;
        while (!terminationCriterion.isTerminated(iteration, population)) {
            System.out.println("Iteration: " + iteration);

            // Create an offspring population
            List<Individual> offspring = new ArrayList<Individual>();

            // Elitism
            if (elitism > 0) {
                List<Individual> populationCopy = new ArrayList<Individual>(population);
                Collections.sort(populationCopy);
                offspring.addAll(populationCopy.subList(0, elitism));
            }

            // Logging
            selected = new TreeMap<Double, Integer>();

            // Breed
            for (int i = 0; i < population.size() / 2; i++) {
                try {
                    // Create jobs for the thread pool
                    jobQueue.put(population);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadedOptimizer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            // Retrieve offspring from thread pool
            for (int i = 0; i < population.size() / 2 * 2; i++) {
                try {
                    // Create jobs for the thread pool
                    offspring.add(offspringQueue.take());
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadedOptimizer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

//            for (Individual individual : population) {
//                System.out.println(individual);
//            }

            // Make offspring population the active population
            population = offspring;

            // Logging
            System.out.println(selected);
//            double[] fitness = new double[population.size()];
//            int i = 0;
//            for (Individual individual : population) {
//                fitness[i++] = individual.getFitness().getDoubleValue();
//            }
//            System.out.println("Chromosome std:" + Arrays.toString(fitness));
            populationCpy = new ArrayList<Individual>(population);
            Collections.sort(populationCpy);
            generationTop = populationCpy.get(0);
            if (generationTop.compareTo(overallTop) < 0) {
                overallTop = generationTop;
            }
            System.out.println("generationTop: " + generationTop);
            System.out.println("overallTop: " + overallTop);

            // Increase iteration counter
            iteration++;
        }

        // Final logging
        //System.out.println(SwissArmyKnife.calculateCoordinates(overallTop.getGenoType().getSnake(), overallTop.getGenoType().getChromosome()));
        //System.out.println(SwissArmyKnife.calculateCoordinatesFromDihedrals(overallTop.getGenoType().getSnake(), overallTop.getGenoType().getChromosome()));
        System.out.println(iteration);

        // Shutdown thread pool
        threadPool.shutdownNow();

    }

}
