/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apslab.cyclops;

/**
 *
 * @author andreas
 */
public class Fitness implements Comparable<Fitness> {

    private double fitness;

    public Fitness(double fitness) {
        this.fitness = fitness;
    }

    public double getDoubleValue() {
        return fitness;
    }

    public int compareTo(Fitness fitnessObj) {
        if (fitness > fitnessObj.getDoubleValue()) {
            return 1;
        }
        else if (fitness < fitnessObj.getDoubleValue()) {
            return -1;
        }
        else {
            return 0;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (!obj.getClass().equals(Fitness.class)) {
            return false;
        }
        return fitness == ((Fitness) obj).getDoubleValue();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.fitness) ^ (Double.doubleToLongBits(this.fitness) >>> 32));
        return hash;
    }

    @Override
    public String toString() {
        return String.valueOf(fitness);
    }

}
