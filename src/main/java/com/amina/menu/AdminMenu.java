package com.amina.menu;

import com.amina.InputDevice;
import com.amina.db.Database;
import com.amina.entities.Customer;
import com.amina.entities.User;

public class AdminMenu extends Menu {
    public AdminMenu(User user) {
        super(user);
    }

    // show options
    @Override
    public void showOptions() {
        // CRUD Customers       Create, Read, Update, Delete
        System.out.println("1. Create Customer");
        System.out.println("2. Read Customer");
        System.out.println("3. Update Customer");
        System.out.println("4. Delete Customer");
        // CRUD Admins         Create, Read, Update, Delete
        System.out.println("5. Create Admin");
        System.out.println("6. Read Admin");
        System.out.println("7. Update Admin");
        System.out.println("8. Delete Admin");
        // CRUD Books           Create, Read, Update, Delete
        System.out.println("9. Create Customer");
        System.out.println("10. Read Customer");
        System.out.println("11. Update Customer");
        System.out.println("12. Delete Customer");
        // CRUD Teas            Create, Read, Update, Delete
        System.out.println("13. Create Customer");
        System.out.println("14. Read Customer");
        System.out.println("15. Update Customer");
        System.out.println("16. Delete Customer");
        // CRUD OrderItems     Create, Read, Update, Delete
        System.out.println("17. Create Customer");
        System.out.println("18. Read Customer");
        System.out.println("19. Update Customer");
        System.out.println("20. Delete Customer");

    }

    // choose an option

    @Override
    public int chooseOption() {

        while (true)
            try {
                return id.inputValueInRange(1, 4, "Please choose option between 1 and 4: ");
            } catch (InputDevice.ValueOutOfRange e) {
                e.printStackTrace();
            }
    }

    @Override
    public void run() {


        try {
            this.db = new Database();
        } catch (Database.DbConnectionError e) {
            throw new RuntimeException(e);
        }

        while (true) {
            showOptions();
            int option = chooseOption();
            switch (option) {
                case 1:
                    createCustomer();
                    break;
                case 2:
                    readCustomer();
                    break;
                case 3:
                    updateCustomer();
                    break;
                case 4:
                    deleteCustomer();
                    break;
                default:
                    // stop the menu
                    return;
            }
        }
    }

    public void createCustomer() {
        Customer customer = id.readCustomer();
        db.createCustomer(customer);
    }
    public void readCustomer() {
        od.showNumberedEntities(db.customers(), "Customers");
    }
    public void updateCustomer() {
        int ID;
        // continuously show all customers, numbered, and choose one of them to delete
        od.showNumberedEntities(db.customers(), "Customers");

        try {
            // print all data before to update
            int index = id.inputValueInRange(0, db.customers().size() - 1, "Choose the index for the customer you want to delete");
            ID = db.customers().get(index).getUser_id();
            // functia din db, singura diferenta fata de deleteCustomer:
            Customer updatedCustomer = id.readCustomer();
            // set the old user id:
            updatedCustomer.setUser_id(ID);
            db.updateCustomer(ID, updatedCustomer);
            // show success message
            od.printMessage("Successfully updated customer with id=" + ID);
        } catch (InputDevice.ValueOutOfRange e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteCustomer() {
        int ID;
        // continuously show all customers, numbered, and choose one of them to delete
        od.showNumberedEntities(db.customers(), "Customers");

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
