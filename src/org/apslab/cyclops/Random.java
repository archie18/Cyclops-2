/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apslab.cyclops;

/**
 * Singleton java.util.Random
 * @author andreas
 */
public class Random {

    private static java.util.Random instance;
    private static Long seed;

    // Prevent external instantiation
    private Random() {
    }

    public static Long getSeed() {
        return seed;
    }

    public static void setSeed(Long seed) {
        Random.seed = seed;
    }

    public static synchronized java.util.Random getInstance() {
        if (instance == null) {
            if (seed != null) {
                instance = new java.util.Random(seed);
            } else {
                instance = new java.util.Random();
            }
        }
        return instance;
    }

}
