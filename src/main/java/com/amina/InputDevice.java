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
        return scan.nextLine();
    }

    public boolean inputBoolean(String message) {
        System.out.println(message);
        return Boolean.parseBoolean(scan.nextLine());//
    }



    public static class ValueOutOfRange extends Exception {
        public ValueOutOfRange(String message) {
            super(message);
        }
    }

    public int inputValueInRange(int a, int b, String msg) throws ValueOutOfRange {
        System.out.print(msg);

        int choice = Integer.parseInt(scan.nextLine());

        if (a <= choice && choice <= b) {
            return choice;
        } else {
            throw new ValueOutOfRange("Value " + choice + " is not inside range (" + a + ", " + b + ")");
        }
    }

    public Customer readCustomer() {
        System.out.print("username: ");
        String username = scan.nextLine();
        System.out.print("password: ");
        String password = scan.nextLine();

        return new Customer(username, password);
    }
}
