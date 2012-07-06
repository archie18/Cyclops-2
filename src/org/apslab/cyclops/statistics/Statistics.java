/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apslab.cyclops.statistics;

import java.util.List;
import org.apslab.cyclops.Individual;

/**
 *
 * @author andreas
 */
public class Statistics {

    OptimizationState optimizationState;

    public Statistics setIteration(int iteration) {
        // Lazy initialization
        if (optimizationState == null) {
            optimizationState = new OptimizationState();
        }
        optimizationState.setIteration(iteration);
        return this;
    }

    public Statistics setParents(List<Individual> parents) {
        // Lazy initialization
        if (optimizationState == null) {
            optimizationState = new OptimizationState();
        }
        optimizationState.setParents(parents);
        return this;
    }

    public Statistics setOffspring(List<Individual> offspring) {
        // Lazy initialization
        if (optimizationState == null) {
            optimizationState = new OptimizationState();
        }
        optimizationState.setOffspring(offspring);
        return this;
    }

    public Statistics run() {
        return this;
    }
}
