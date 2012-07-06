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
public interface IPopulationInitializer {

    public List<Individual> getPopulation();
    
    public IPopulationInitializer setPrototypeIndividual(Individual individual);

}
