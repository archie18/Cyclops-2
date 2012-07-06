/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apslab.cyclops;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author andreas
 */
public class SwissArmyKnife {

    public static String booleanArrayToString(boolean[] arr) {
        StringBuilder sb  = new StringBuilder();
        for (boolean b : arr) {
            if (b) {
                sb.append(1);
            } else {
                sb.append(0);
            }
        }
        return sb.toString();
    }

    public static List<Individual> createPopulation(Individual... individuals) {
        List<Individual> population = new ArrayList<Individual>();
        population.addAll(Arrays.asList(individuals));
        return population;
    }

}
