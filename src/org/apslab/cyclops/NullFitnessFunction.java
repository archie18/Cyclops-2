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
public class NullFitnessFunction implements IFitnessFunction {

    public Individual calculate(Individual individual) {
        individual.setFitness(new Fitness(Double.NaN));
        return individual;
    }

    public List<Individual> calculate(List<Individual> individuals) {
        for (Individual individual : individuals) {
            individual = calculate(individual);
        }
        return individuals;
    }

}
