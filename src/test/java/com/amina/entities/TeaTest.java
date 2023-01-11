package com.amina.entities;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TeaTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public TeaTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(TeaTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void test() {
        Tea x = new Tea();
        x.setTea_id(1);
        x.setBrand("Treasure Island");
        x.setFlavor("Unpeeled Oranges");
        x.setPrice(2);
        assertEquals(
                x.toString(),
                "Book(TEA_ID=1,brand=Treasure Island,flavor=Unpeeled Oranges,price=2.0)"
        );
    }
}