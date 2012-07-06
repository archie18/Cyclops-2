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

/**
 * Genetic algorithm implementation
 * @author andreas
 */
public class Optimizer {
    private int elitism;
    private IPopulationInitializer populationInitializer;
    private IGeneticOperator crossOverOperator;
    private IGeneticOperator mutationOperator;
    private ISelector selector;
    private IFitnessFunction fitnessFunction;
    private ITerminationCriterion terminationCriterion;

    public Optimizer setElitism(int elitism) {
        this.elitism = elitism;
        return this;
    }

    public Optimizer setPopulationInitializer(IPopulationInitializer populationInitializer) {
        this.populationInitializer = populationInitializer;
        return this;
    }

    public Optimizer setCrossOverOperator(IGeneticOperator crossOverOperator) {
        this.crossOverOperator = crossOverOperator;
        return this;
    }

    public Optimizer setMutationOperator(IGeneticOperator mutationOperator) {
        this.mutationOperator = mutationOperator;
        return this;
    }

    public Optimizer setSelector(ISelector selector) {
        this.selector = selector;
        return this;
    }

    public Optimizer setFitnessFunction(IFitnessFunction fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
        return this;
    }

    public Optimizer setTerminationCriterion(ITerminationCriterion terminationCriterion) {
        this.terminationCriterion = terminationCriterion;
        return this;
    }

    public void optimize() {
        Individual generationTop;
        Individual overallTop;


        // Repeat initialization until one "active" individual is found
        List<Individual> population = null;
        boolean found = false;
        do {
            System.out.println("Initializing...");
            // Initialize population
            population = populationInitializer.getPopulation();

            // Evaluate initial population
            population = fitnessFunction.calculate(population);

            for (Individual individual : population) {
                //individual = fitnessFunction.calculate(individual);
                System.out.println(individual);
                if (!individual.getFitness().equals(new Fitness(1000.0))) {
                    found = true;
                }
            }
        } while (!found);

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
            Map<Double, Integer> selected = new TreeMap<Double, Integer>();

            // Breed
            for (int i = 0; i < population.size() / 2; i++) {
                Individual papa = selector.select(population);
                Individual mama = selector.select(population);

                // Logging
                if (selected.get(papa.getFitness().getDoubleValue()) == null) {
                    selected.put(papa.getFitness().getDoubleValue(), 1);
                } else {
                    selected.put(papa.getFitness().getDoubleValue(), selected.get(papa.getFitness().getDoubleValue()) + 1);
                }
                if (selected.get(mama.getFitness().getDoubleValue()) == null) {
                    selected.put(mama.getFitness().getDoubleValue(), 1);
                } else {
                    selected.put(mama.getFitness().getDoubleValue(), selected.get(mama.getFitness().getDoubleValue()) + 1);
                }

                // Cross-over
                List<Individual> children = crossOverOperator.apply(papa, mama);
                Individual son = children.get(0);
                Individual daughter = children.get(1);

                // Mutation
                son = mutationOperator.apply(son).get(0);
                daughter = mutationOperator.apply(daughter).get(0);

                // Evaluate fitness
                //son = fitnessFunction.calculate(son);
                //daughter = fitnessFunction.calculate(daughter);

                // Add offspring to offspring population
                offspring.add(son);
                offspring.add(daughter);
            }

            // Evaluate fitness
            offspring = fitnessFunction.calculate(offspring);

            // Make offspring population the active population
            population = offspring;

            // Logging
            System.out.println("Selected: " + selected);
            for (Individual individual : population) {
                System.out.println(individual);
            }
//            Matrix fitness = MatrixFactory.dense(population.size(), population.get(0).getGenoType().getChromosome().length);
//            for (int i = 0; i < population.size()); i++) {
//                for (int j = 0; j < population.get(i).getGenoType().getChromosome().length; j++) {
//                fitness.
//                
//            }
//
//            }
//            System.out.println("Chromosome std:" + Arrays.toString(std.evaluate(fitness)));
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

    }

}
