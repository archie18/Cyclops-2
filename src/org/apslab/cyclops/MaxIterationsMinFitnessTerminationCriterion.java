/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apslab.cyclops;

import java.util.List;

/**
 *
 * @author andreas
 */
public class MaxIterationsMinFitnessTerminationCriterion implements ITerminationCriterion {

    private int maxIterations;
    private Fitness minFitness;

    public MaxIterationsMinFitnessTerminationCriterion setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    public MaxIterationsMinFitnessTerminationCriterion setMinFitness(Fitness minFitness) {
        this.minFitness = minFitness;
        return this;
    }

    public boolean isTerminated(int iteration, List<Individual> population) {
        if (iteration >= maxIterations) {
            return true;
        }
        for (Individual individual : population) {
            if (individual.getFitness().compareTo(minFitness) <= 0) {
                return true;
            }
        }
        return false;
    }

}
