package com.amina;

import com.amina.entities.Customer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class InputDevice {
        private Scanner scan;

    public InputDevice() {
        this.scan = new Scanner(System.in);
    }

    public String inputString(String prompt) {
        System.out.print(prompt);
        return scan.next();
    }

    public boolean inputBoolean(String message) {
        System.out.println(message);
        return scan.nextBoolean();
    }

    // Exception class
    public static class ValueOutOfRange extends Exception {
        public ValueOutOfRange(String message) {
            super(message);
        }
    }

    public int inputValueInRange(int a, int b, String msg) throws ValueOutOfRange {
        System.out.print(msg);

        int choice = scan.nextInt();

        if (a <= choice && choice <= b) {
            return choice;
        } else {
            throw new ValueOutOfRange("Value " + choice + " is not inside range (" + a + ", " + b + ")");
        }
    }

    public Customer readCustomer() {
        System.out.print("username: ");
        String username = scan.next(); // reads only one word, meaning that it stops on first whitespace
        System.out.print("password: ");
        String password = scan.next(); // reads only one word, meaning that it stops on first whitespace

        return new Customer(username, password);
    }
}
