/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apslab.cyclops;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andreas
 */
public class BitArrayRandomPopulationInitializer implements IPopulationInitializer {

    private int populationSize;
    private int arrayLength;
    private Individual prototypeIndividual;

    public BitArrayRandomPopulationInitializer setArrayLength(int arrayLength) {
        this.arrayLength = arrayLength;
        return this;
    }

    public BitArrayRandomPopulationInitializer setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
        return this;
    }

    public List<Individual> getPopulation() {
        List<Individual> population = new ArrayList<Individual>();
        for (int i = 0; i < populationSize; i++) {
            boolean[] bitArray = new boolean[arrayLength];
            for (int j = 0; j < arrayLength; j++) {
                bitArray[j] = Random.getInstance().nextBoolean();
            }
            BitArrayGenoType genoType = new BitArrayGenoType().setChromosome(bitArray);
            Individual individual = prototypeIndividual.getInstance().setGenoType(genoType);
            population.add(individual);
        }
        return population;
    }

    public BitArrayRandomPopulationInitializer setPrototypeIndividual(Individual individual) {
        this.prototypeIndividual = individual;
        return this;
    }



}
