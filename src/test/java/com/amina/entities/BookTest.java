package com.amina.entities;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BookTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public BookTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(BookTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void test() {
        
    }
}