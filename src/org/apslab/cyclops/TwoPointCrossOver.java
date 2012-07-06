/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apslab.cyclops;

import java.util.ArrayList;
import java.util.List;

/**
 * Two-point genetic algorithm cross-over operator
 * @author andreas
 */
public class TwoPointCrossOver implements IGeneticOperator {

    public List<Individual> apply(Individual... parents) {
        Individual papa = parents[0];
        Individual mama = parents[1];

        int xOverPoint1 = 1 + Random.getInstance().nextInt(((boolean[]) papa.getGenoType().getChromosome()).length - 2);
        int xOverPoint2 = xOverPoint1 + 1 + Random.getInstance().nextInt(((boolean[]) papa.getGenoType().getChromosome()).length - xOverPoint1 - 1);

        IGenoType sonGenoType = papa.getGenoType().clone();
        boolean[] sonChromosome = (boolean[]) sonGenoType.getChromosome();
        System.arraycopy((boolean[]) mama.getGenoType().getChromosome(), xOverPoint1, sonChromosome, xOverPoint1, xOverPoint2 - xOverPoint1);
        Individual son = papa.getInstance().setGenoType(sonGenoType);

        IGenoType daughterGenoType = mama.getGenoType().clone();
        boolean[] daughterChromosome = (boolean[]) daughterGenoType.getChromosome();
        System.arraycopy((boolean[]) papa.getGenoType().getChromosome(), xOverPoint1, daughterChromosome, xOverPoint1, xOverPoint2 - xOverPoint1);
        Individual daughter = papa.getInstance().setGenoType(daughterGenoType);

        List<Individual> offspring = SwissArmyKnife.createPopulation(son, daughter);
        return offspring;
    }

}
