package com.amina;

import com.amina.db.Database;
import com.amina.entities.Admin;
import com.amina.entities.Customer;
import com.amina.entities.User;
import com.amina.menu.AdminMenu;
import com.amina.menu.CustomerMenu;

public class App {
    public App() {
        this.id = new InputDevice();
    }

    public static class LoginException extends Exception {
        public LoginException(String message) {
            super(message);
        }
    }

    private User loggedUser;
    private InputDevice id;
    private OutputDevice od = new OutputDevice();

    public InputDevice getId() {
        return id;
    }

    public void login() throws LoginException { // decoupled
        od.printMessage("\nPlease enter your username and password :)");
        String username = id.inputString("Username:");
        String password = id.inputString("Password:");
        this.loggedUser = Database.login(username, password);
        if (this.loggedUser == null) {
            throw new LoginException("Credentials for user with username=" + username +" and password=" + password + " Failed.");
        }
    }

    public void run() {
        if (loggedUser instanceof Admin)
            new AdminMenu(loggedUser).run();
        else if (loggedUser instanceof Customer) {
            new CustomerMenu(loggedUser).run();
        }
    }


    public static void main(String[] args) {
        App app = new App();
        final String CONFIG_FILE_PATH = ".config";
        String databaseConnectionUrl = null;
        int cntTries = 0;

        while (true) {
            try {
                // check if the config file exists and offer the option to use a new database config.
                if (ConfigUtils.checkIfConfigFileExists(CONFIG_FILE_PATH)) {
                    // the user will either use the file, or create a new one
                    if (app.getId().inputBoolean("Do you want to use the existing Database Configuration? (true/false)")) {
                        databaseConnectionUrl = ConfigUtils.parseDatabaseConnectionUrl(CONFIG_FILE_PATH);
                    } else {
                        databaseConnectionUrl = ConfigUtils.createNewConfigFileAndReturnConnectionUrl(CONFIG_FILE_PATH);
                    }
                } else {
                    // the user must create a new file
                    databaseConnectionUrl = ConfigUtils.createNewConfigFileAndReturnConnectionUrl(CONFIG_FILE_PATH);
                }
                Database.setDbConnectionUrl(databaseConnectionUrl);

                // after database configuration:

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