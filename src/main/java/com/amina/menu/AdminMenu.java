package com.amina.menu;

import com.amina.InputDevice;
import com.amina.entities.Customer;

public class AdminMenu extends Menu {
    // show options
    public void showOptions() {

        System.out.println("1. Delete Customer"); // CRUD   Create, Receive, Update, Delete
        System.out.println("2. Add orderItem");
        System.out.println("3. Delete orderItem");
        System.out.println("4. Update orderItem");

    }
    // choose an option
    public int chooseOption() {

        while(true)
            try {
                id.inputValueInRange(1, 4, "Please choose option between 1 and 4: ");
            } catch (InputDevice.ValueOutOfRange e) {
                e.printStackTrace();
            }
        }


    public void run() {
        showOptions();
        int option = chooseOption();
        switch (option) {
            case 1:
                deleteCustomerById();
                break;
        }
    }

    public void createCustomer() {
        Customer customer = id.readCustomer();
        db.createCustomer(customer);
    }

    public void readCustomers() {
        od.printMessage("Showing all customers:");
        od.showNumberedEntities(db.customers());
    }

    public void updateCustomerById() {
        int ID;
        // continuously show all customers, numbered, and choose one of them to delete
        od.showNumberedEntities(db.customers());

        try {
            // print all data before to update
            int index = id.inputValueInRange(0, db.customers().size() - 1, "Choose the index for the customer you want to delete");
            ID = db.customers().get(index).getUser_id();
            Customer updatedCustomer = id.readCustomer();
            db.updateCustomer(ID, updatedCustomer);
            // show success message
            od.printMessage("Successfully deleted customer with id=" + ID);
        } catch (InputDevice.ValueOutOfRange e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCustomerById() {
        int ID;
        // continuously show all customers, numbered, and choose one of them to delete
        od.showNumberedEntities(db.customers());

        try {
            // print all data before to update
            int index = id.inputValueInRange(0, db.customers().size() - 1, "Choose the index for the customer you want to delete");
            ID = db.customers().get(index).getUser_id();
            // using method from database
            db.deleteCustomer(ID);
            // print message
            od.printMessage("Successfully deleted customer with id=" + ID);
        } catch (InputDevice.ValueOutOfRange e) {
            throw new RuntimeException(e);
        }
    }


}
