package com.amina;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigUtilsTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ConfigUtilsTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(ConfigUtilsTest.class);
    }

    /**
     * Rigourous Test
     */
    public void test() {
        // find tmp config file and delete it if exists
        String configFilePath = ".config.tmp";
        File file = new File(configFilePath);
        file.delete();
        assertFalse(ConfigUtils.checkIfConfigFileExists(configFilePath));

        // check the config file parser
        try {
            // create the file with default data
            FileWriter fw = new FileWriter(configFilePath);
            fw.write("3306\n" +
                    "final_project\n" +
                    "root\n" +
                    "\n");
            fw.close();

            // check that config file exists:
            assertTrue(ConfigUtils.checkIfConfigFileExists(configFilePath));

            // check that the connection url can be parsed from the file
            String connUrl = ConfigUtils.parseDatabaseConnectionUrl(configFilePath);
            assertEquals(connUrl, "jdbc:mysql://root:@localhost:3306/final_project");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}