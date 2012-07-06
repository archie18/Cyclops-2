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
public class OptimizationState {

    int iteration;
    private List<Individual> parents;
    private List<Individual> offspring;

    public OptimizationState setIteration(int iteration) {
        this.iteration = iteration;
        return this;
    }

    public OptimizationState setParents(List<Individual> parents) {
        this.parents = parents;
        return this;
    }

    public OptimizationState setOffspring(List<Individual> offspring) {
        this.offspring = offspring;
        return this;
    }
}
