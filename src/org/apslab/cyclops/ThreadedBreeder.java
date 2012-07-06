/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apslab.cyclops;

import java.util.List;
import java.util.concurrent.BlockingQueue;

/**
 *
 * @author andreas
 */
public class ThreadedBreeder implements Runnable {
    
    private ISelector selector;
    private IGeneticOperator crossOverOperator;
    private Mutation mutationOperator;
    private IFitnessFunction fitnessFunction;
    private BlockingQueue<List<Individual>> jobQueue;
    private BlockingQueue<Individual> offspringQueue;
    private static int number = 0;
    private String name;

    public ThreadedBreeder() {
        name = "Thread" + number++;
    }

    public ThreadedBreeder setCrossOverOperator(IGeneticOperator crossOverOperator) {
        this.crossOverOperator = crossOverOperator;
        return this;
    }

    public ThreadedBreeder setMutationOperator(Mutation mutationOperator) {
        this.mutationOperator = mutationOperator;
        return this;
    }

    public ThreadedBreeder setSelector(ISelector selector) {
        this.selector = selector;
        return this;
    }

    public ThreadedBreeder setFitnessFunction(IFitnessFunction fitnessFunction) {
        this.fitnessFunction = fitnessFunction;
        return this;
    }

    public ThreadedBreeder setJobQueue(BlockingQueue<List<Individual>> jobQueue) {
        this.jobQueue = jobQueue;
        return this;
    }

    public ThreadedBreeder setOffspringQueue(BlockingQueue<Individual> offspringQueue) {
        this.offspringQueue = offspringQueue;
        return this;
    }

    public void run() {
        try {
            while (true) {
                // Wait for job
                List<Individual> population = jobQueue.take();
                //System.out.println(name);

                // Breed
                Individual papa = selector.select(population);
                Individual mama = selector.select(population);

                // Cross-over
                List<Individual> children = crossOverOperator.apply(papa, mama);
                Individual son = children.get(0);
                Individual daughter = children.get(1);

                // Mutation
                son = mutationOperator.apply(son).get(0);
                daughter = mutationOperator.apply(daughter).get(0);

                // Evaluate fitness
                son = fitnessFunction.calculate(son);
                daughter = fitnessFunction.calculate(daughter);

                // Logging
                // WARNING: needs to be done BEFORE touching the offspringQueue
                // otherwise we get concurrency problems
                List<Individual> parents = SwissArmyKnife.createPopulation(papa, mama);
                ThreadedOptimizer.addParents(parents);

                // Put offspring into offspring queue
                offspringQueue.put(son);
                offspringQueue.put(daughter);
            }
        } catch (InterruptedException ex) {
            //Logger.getLogger(ThreadedOptimizer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
