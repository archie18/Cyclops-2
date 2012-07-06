/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.apslab.cyclops;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author andreas
 */
public class SwissArmyKnifeTest {

    public SwissArmyKnifeTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of booleanArrayToString method, of class SwissArmyKnife.
     */
    @Test
    public void testBooleanArrayToString() {
        System.out.println("booleanArrayToString");
        boolean[] arr = new boolean[] {false, true, false, true, false};
        String expResult = "01010";
        String result = SwissArmyKnife.booleanArrayToString(arr);
        assertEquals(expResult, result);
    }

}