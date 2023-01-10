package com.amina;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ConfigUtils {
    public static boolean checkIfConfigFileExists(String CONFIG_FILE_PATH) {
        File f = new File(CONFIG_FILE_PATH);
        if(f.exists() && !f.isDirectory()) {
            return true;
        }

        return false;
    }

    public static String parseDatabaseConnectionUrl(String CONFIG_FILE_PATH) {

        try {
            File file = new File(CONFIG_FILE_PATH);
            Scanner input = new Scanner(file);

            // if the file exists, the code below runs
            String port = input.nextLine();
            String database = input.nextLine();
            String username = input.nextLine();
            String password = input.nextLine();

            input.close();

            return String.format(
                    "jdbc:mysql://%s:%s@localhost:%s/%s",
                    username, password, port, database
            );


        } catch (FileNotFoundException ex) {
            // if the file does not exist, the code below runs
            return createNewConfigFileAndReturnConnectionUrl(CONFIG_FILE_PATH);
        }


    }

    // will create the new file at the param's path,
    //  and also ask the user for the configuration data (database, port, username, password)
    public static String createNewConfigFileAndReturnConnectionUrl(String CONFIG_FILE_PATH) {
        try {
            File configFile = new File(CONFIG_FILE_PATH);
            configFile.createNewFile();
            // ask the user for the new data, and save it to the config file:
            FileWriter configFileWriter = new FileWriter(configFile);

            InputDevice id = new InputDevice();
            String port = id.inputString("Database port: ");
            String database = id.inputString("Database name: ");
            String username = id.inputString("Database username: ");
            String password = id.inputString("Database password: ");
            // save data to config file
            configFileWriter.write(port);
            configFileWriter.write("\n");
            configFileWriter.write(database);
            configFileWriter.write("\n");
            configFileWriter.write(username);
            configFileWriter.write("\n");
            configFileWriter.write(password);
            configFileWriter.write("\n");
            configFileWriter.close();
            // and return the new connection url
            return String.format(
                    "jdbc:mysql://%s:%s@localhost:%s/%s",
                    username, password, port, database
            );
        } catch (IOException e) {
            System.out.println("An error occurred while configuring the Application.");
            e.printStackTrace();
            return null;
        }
    }
}
