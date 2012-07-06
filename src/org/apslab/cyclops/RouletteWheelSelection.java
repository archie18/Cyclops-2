/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apslab.cyclops;

import java.util.Arrays;
import java.util.List;

/**
 * Genetic algorithm roulette wheel selection
 * @author andreas
 */
public class RouletteWheelSelection implements ISelector {

    private double[] reciprocal(List<Individual> population) {
        double[] reciprocalFitness = new double[population.size()];
        int i = 0;
        for (Individual individual : population) {
            reciprocalFitness[i++] = 1.0 / individual.getFitness().getDoubleValue();
        }
        return reciprocalFitness;
    }

    private double totalFitness(double[] fitness) {
        double totalFitness = 0;
        for (int i = 0; i < fitness.length; i++) {
            totalFitness += fitness[i];
        }
        return totalFitness;
    }

    private double[] accumulatedFitness(double[] reciprocalFitness) {
        double[] accumulatedFitnesses = new double[reciprocalFitness.length];
        double accumulatedFitness = 0;
        for (int i = 0; i < reciprocalFitness.length; i++) {
            accumulatedFitness += reciprocalFitness[i];
            accumulatedFitnesses[i] = accumulatedFitness;
        }
        return accumulatedFitnesses;
    }

    public Individual select(List<Individual> population) {
        double[] reciprocalFitness = reciprocal(population);
        double totalFitness = totalFitness(reciprocalFitness);
        double[] accumulatedFitness = accumulatedFitness(reciprocalFitness);
        //System.out.println("reciprocalFitness=" + Arrays.toString(reciprocalFitness));
        //System.out.println("totalFitness=" + totalFitness);
        //System.out.println("accumulatedFitness=" + Arrays.toString(accumulatedFitness));

        // Perform selection
        double r = Random.getInstance().nextDouble() * totalFitness;
        int i;
        for (i = 0; i < accumulatedFitness.length; i++) {
            if (accumulatedFitness[i] >= r) {
                break;
            }
        }
        if (i == accumulatedFitness.length) {
            i--;
        }
        return population.get(i);
    }

}
