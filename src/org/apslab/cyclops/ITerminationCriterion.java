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
public interface ITerminationCriterion {

    boolean isTerminated(int iteration, List<Individual> population);

}
