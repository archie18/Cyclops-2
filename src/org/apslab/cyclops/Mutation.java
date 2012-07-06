/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apslab.cyclops;

import java.util.ArrayList;
import java.util.List;

/**
 * n-point mutation genetic operator
 * @author andreas
 */
public class Mutation implements IGeneticOperator {

    private double probability;
    private int n;

    public Mutation setN(int n) {
        this.n = n;
        return this;
    }

    public Mutation setProbability(double probability) {
        this.probability = probability;
        return this;
    }

    public List<Individual> apply(Individual... individuals) {
        Individual individual = individuals[0];
        
        double r;
        if (probability == 1.0) {
            r = 0.0;
        } else {
            r = Random.getInstance().nextDouble();
        }

        boolean[] newChromosome = ((boolean[]) individual.getGenoType().getChromosome()).clone();
        if (r < probability) {
            for (int i = 0; i < n; i++) {
                int rInt = Random.getInstance().nextInt(newChromosome.length);
                newChromosome[rInt] = !newChromosome[rInt];
            }
        }

        IGenoType genoType = individual.getGenoType().clone();
        genoType.setChromosome(newChromosome);
        Individual offspring = individual.getInstance().setGenoType(genoType);
        return SwissArmyKnife.createPopulation(offspring);
    }

}
