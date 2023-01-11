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

        Book x = new Book();
        x.setBook_id(1);
        x.setTitle("Treasure Island");
        x.setAuthor("Mark Not Twain");
        x.setPrice(29);
        assertEquals(
                x.toString(),
                "Book(ID=1,title=Treasure Island,author=Mark Not Twain,price=29)"
        );
    }
}