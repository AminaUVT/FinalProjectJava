package com.amina.db;

import com.amina.AppTest;
import com.amina.entities.Book;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import javax.xml.crypto.Data;
import java.sql.SQLException;
import java.util.concurrent.ThreadLocalRandom;

public class DatabaseTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public DatabaseTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(DatabaseTest.class);
    }

    /**
     * Rigourous Test
     */
    public void testDatabaseLoad() {
        try {


            String dbUrl = "jdbc:mysql://root:34213421@localhost:3306",
            dbName = String.format("testABC%d", ThreadLocalRandom.current().nextInt(100, 999 + 1));

            Database.createAndPopulateDb(dbUrl, dbName);

            Database.setDbConnectionUrl(
                    String.format("%s/%s", dbUrl, dbName)
            );

            Database db = new Database();

        } catch (Database.DbConnectionError e) {
            fail();
            throw new RuntimeException(e);
        } catch (SQLException e) {
            fail();
            throw new RuntimeException(e);
        }

    }
}