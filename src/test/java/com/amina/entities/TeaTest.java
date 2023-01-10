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
        
    }
}