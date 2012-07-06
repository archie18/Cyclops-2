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
public interface IGeneticOperator {

    List<Individual> apply(Individual... parents);

}
