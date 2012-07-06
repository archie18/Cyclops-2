/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apslab.cyclops;

import java.util.List;

/**
 * Two-point genetic algorithm cross-over operator
 * @author andreas
 */
public class NullCrossOver implements IGeneticOperator {

    public List<Individual> apply(Individual... parents) {
        Individual papa = parents[0];
        Individual mama = parents[1];

        IGenoType sonGenoType = papa.getGenoType().clone();
        Individual son = papa.getInstance().setGenoType(sonGenoType);
        IGenoType daughterGenoType = mama.getGenoType().clone();
        Individual daughter = mama.getInstance().setGenoType(daughterGenoType);

        return SwissArmyKnife.createPopulation(son, daughter);
    }

}
