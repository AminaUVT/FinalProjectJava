package com.amina.entities;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UserTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public UserTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(UserTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void test() {
        User x = new User();
        x.setUser_id(1);
        x.setUsername("Rex");
        x.setPassword("T-Rex");
        assertEquals(
                x.toString(),
                "User(ID=1,username=Rex,password=T-Rex)"
        );
    }
}