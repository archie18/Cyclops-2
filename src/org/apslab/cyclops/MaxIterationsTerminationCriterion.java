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
public class MaxIterationsTerminationCriterion implements ITerminationCriterion {

    private int maxIterations;

    public MaxIterationsTerminationCriterion setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;
        return this;
    }

    public boolean isTerminated(int iteration, List<Individual> population) {
        if (iteration >= maxIterations) {
            return true;
        }
        return false;
    }

}
