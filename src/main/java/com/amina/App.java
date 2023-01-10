package com.amina;

import com.amina.entities.User;
import jdk.jpackage.internal.Log;

/**
 * Hello world!
 */


public class App {
    public static class LoginException extends Exception {
        public LoginException(String message) {
            super(message);
        }
    }

    private User loggedUser;

    public void login() throws LoginException {
    }

    public void run() {

    }

    public static void main(String[] args) {
        App app = new App();
        int cntTries = 0;

        while (true) {
            try {
                app.login(); // may throw an error

                cntTries = 0; // because app.login() didn't throw an error, so it worked
                app.run();

            } catch (LoginException e) {
                e.printStackTrace();
                cntTries++; // each login error means that the user credentials were wrong :).
                if (cntTries == 3) {
                    System.out.println("Because you failed logging in for three consecutive attempts, the application stopped!");
                    return;
                }
            }
        }


    }

}