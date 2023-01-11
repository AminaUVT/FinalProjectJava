package com.amina;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class InputDeviceTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public InputDeviceTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(InputDeviceTest.class);
    }

    /**
     * Rigourous Test
     */
    public void test() {
        InputDevice id = new InputDevice();

        assertTrue(id.inputString("Type String:") instanceof String);
        assertTrue((Boolean)id.inputBoolean("Type Boolean (true/false):") instanceof Boolean);
        try {
            assertTrue((Integer)id.inputValueInRange(2, 10, "Type value between 2 and 10:") instanceof Integer);
        } catch (InputDevice.ValueOutOfRange e) {
            fail();
        }
    }
}