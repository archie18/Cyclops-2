/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apslab.cyclops;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Genetic algorithm tournament selection
 * @author andreas
 */
public class TournamentSelection implements ISelector {

    private int tournamentSize;

    public int getTournamentSize() {
        return tournamentSize;
    }

    public TournamentSelection setTournamentSize(int tournamentSize) {
        this.tournamentSize = tournamentSize;
        return this;
    }

    public Individual select(List<Individual> population) {
        // Select tournament participants
        List<Individual> tournament = new ArrayList<Individual>();
        for (int i = 0; i < tournamentSize; i++) {
            int r = Random.getInstance().nextInt(population.size());
            tournament.add(population.get(r));
        }

        // Select best chromosome
        Collections.sort(tournament);
        return tournament.get(0);
    }

}
