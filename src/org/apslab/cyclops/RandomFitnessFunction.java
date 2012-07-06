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
public class RandomFitnessFunction implements IFitnessFunction {

    public Individual calculate(Individual individual) {
        individual.setFitness(new Fitness(Random.getInstance().nextDouble()));
        return individual;
    }

    public List<Individual> calculate(List<Individual> individuals) {
        for (Individual individual : individuals) {
            individual = calculate(individual);
        }
        return individuals;
    }

}
