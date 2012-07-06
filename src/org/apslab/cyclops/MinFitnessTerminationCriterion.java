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
public class MinFitnessTerminationCriterion implements ITerminationCriterion {
    private Fitness minFitness;

    public MinFitnessTerminationCriterion setMinFitness(Fitness minFitness) {
        this.minFitness = minFitness;
        return this;
    }

    public boolean isTerminated(int iteration, List<Individual> population) {
        for (Individual individual : population) {
            if (individual.getFitness().compareTo(minFitness) <= 0) {
                return true;
            }
        }
        return false;
    }

}
