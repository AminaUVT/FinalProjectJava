package com.amina.entities;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class CustomerTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public CustomerTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(CustomerTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void test() {

        Customer x = new Customer();
        x.setUser_id(1);
        x.setUsername("Treasure Island");
        x.setPassword("Mark Not Twain");
        assertEquals(
                x.toString(),
                "Customer(ID=1,username=Treasure Island,password=Mark Not Twain)"
        );
    }
}