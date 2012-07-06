/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apslab.cyclops;

/**
 *
 * @author andreas
 */
public class BitArrayGenoType implements IGenoType<boolean[]> {
    protected boolean[] chromosome;

    public boolean[] getChromosome() {
        return chromosome;
    }

    public BitArrayGenoType setChromosome(boolean[] chromosome) {
        this.chromosome = chromosome;
        return this;
    }

    @Override
    public String toString() {
        return SwissArmyKnife.booleanArrayToString(chromosome);
    }

    /**
     * Returns a deep clone
     * @return a deep clone
     */
    @Override
    public BitArrayGenoType clone() {
        return new BitArrayGenoType().setChromosome(chromosome.clone());
    }

    public BitArrayGenoType getInstance() {
        return new BitArrayGenoType();
    }

}
