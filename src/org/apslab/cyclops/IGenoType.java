/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apslab.cyclops;

/**
 *
 * @author andreas
 */
public interface IGenoType<T> extends Cloneable {

    public T getChromosome();

    public IGenoType setChromosome(T chromosome);

    public IGenoType getInstance();

    public IGenoType clone();

}
