package com.amina.entities;

import com.amina.AppTest;
import com.amina.entities.Book;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

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
        
    }
}