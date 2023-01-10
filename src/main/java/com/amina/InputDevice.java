package com.amina;

import java.util.Scanner;

public class InputDevice {

    private Scanner scan;

    public InputDevice() {
        this.scan = new Scanner(System.in);
    }

    // clasa de exceptie
    public static class ValueOutOfRange extends Exception {
        public ValueOutOfRange(String message) {
            super(message);
        }
    }

    int inputValueInRange(int a, int b, String msg) throws ValueOutOfRange {
        System.out.print(msg);

        int choice = scan.nextInt();

        if (a <= choice && choice <= b) {
            return choice;
        } else {
            throw new ValueOutOfRange("Value "+ choice + " is not inside range (" + a + ", " + b + ")");
        }
    }
}
