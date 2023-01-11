package com.amina.entities;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AdminTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AdminTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AdminTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void test() {
        Admin x = new Admin();
        x.setUser_id(1);
        x.setUsername("Amina");
        x.setPassword("Parrot");
        assertEquals(
                x.toString(),
                "Admin(ID=1,username=Amina,password=Parrot)"
                );
    }
}