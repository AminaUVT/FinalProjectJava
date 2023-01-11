package com.amina;

import com.amina.entities.Book;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;


public class OutputDeviceTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public OutputDeviceTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(OutputDeviceTest.class);
    }

    /**
     * Rigourous Test
     */
    public void test() {

        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        final String utf8 = StandardCharsets.UTF_8.name();

        try {
            PrintStream ps = new PrintStream(baos, true, utf8);
            System.setOut(ps);

            OutputDevice od = new OutputDevice();

            od.printMessage("abc");
            var books = Arrays.stream(new Book[]{
                    new Book(),
                    new Book(),
            }).toList();
            books.get(0).setAuthor("A");
            books.get(1).setAuthor("B");
            books.get(0).setTitle("tA");
            books.get(1).setTitle("tB");
            books.get(0).setPrice(123);
            books.get(1).setPrice(321);
            od.showNumberedEntities(
                    books,
                    "Books"
            );

            String out = baos.toString();

            assertEquals(
                    out,
                    "abc\r\n" +
                            "List of all Books:\r\n" +
                            "0. Book(ID=0,title=tA,author=A,price=123)\r\n" +
                            "1. Book(ID=0,title=tB,author=B,price=321)\r\n"
            );

        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

    }
}