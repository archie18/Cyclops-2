/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apslab.cyclops;

/**
 *
 * @author andreas
 */
public class Individual implements Comparable<Individual> {

    private IGenoType genoType;
    private Fitness fitness;

    public Fitness getFitness() {
        return fitness;
    }

    public Individual setFitness(Fitness fitness) {
        this.fitness = fitness;
        return this;
    }

    public IGenoType getGenoType() {
        return genoType;
    }

    public Individual setGenoType(IGenoType genoType) {
        this.genoType = genoType;
        return this;
    }

    public int compareTo(Individual individual) {
        return fitness.compareTo(individual.getFitness());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(genoType);
        sb.append(" ");
        sb.append("Fitness: ");
        sb.append(fitness);
        return sb.toString();
    }

    public Individual getInstance() {
        return new Individual();
    }

}
