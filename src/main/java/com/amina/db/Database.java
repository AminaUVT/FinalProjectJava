package com.amina.db;

import com.amina.entities.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private List<Customer> customers;
    private List<Admin> admins;
    private List<OrderItem> orderItems;
    private List<Tea> teas;
    private List<Book> books;

    public List<Customer> customers() {
        return customers;
    }

    public List<Admin> admins() {
        return admins;
    }

    public List<OrderItem> orderItems() {
        return orderItems;
    }

    public List<Tea> teas() {
        return teas;
    }

    public List<Book> books() {
        return books;
    }

    // constructor:
    private Connection conn;

    public void loadDb() throws DbConnectionError {
        admins = new ArrayList<Admin>();
        customers = new ArrayList<Customer>();

        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from admins");
            while (rs.next()) {
                Admin admin = new Admin();
                // inside the DB user_id is generated automatically,
                //  but we still need to load it into our program from the database
                admin.setUser_id(rs.getInt("user_id"));
                admin.setUsername(rs.getString("username"));
                admin.setPassword(rs.getString("password"));
                admins.add(admin);
            }

        } catch (SQLException e) {
            throw new DbConnectionError("Couldn't load data from the table admins");
        }

        try {
            stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from customers");
            while (rs.next()) {
                Customer customer = new Customer();
                // inside the DB user_id is generated automatically,
                //  but we still need to load it into our program from the database
                customer.setUser_id(rs.getInt("user_id"));
                customer.setUsername(rs.getString("username"));
                customer.setPassword(rs.getString("password"));
                customers.add(customer);
            }


        } catch (SQLException e) {
            throw new DbConnectionError("Couldn't load data from the table customers");
        }


        try {
            stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Tea");
            while (rs.next()) {
                Tea tea = new Tea();
                // inside the DB user_id is generated automatically,
                //  but we still need to load it into our program from the database
                tea.setTea_id(rs.getInt("tea_id"));
                tea.setBrand(rs.getString("brand"));
                tea.setFlavor(rs.getString("flavor"));
                tea.setPrice(rs.getInt("price"));
                teas.add(tea);
            }


        } catch (SQLException e) {
            throw new DbConnectionError("Couldn't load data from the table Tea");
        }

        //for Book

        try {
            stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Book");
            while (rs.next()) {
                Book book = new Book();
                // inside the DB user_id is generated automatically,
                //  but we still need to load it into our program from the database
                book.setBook_id(rs.getInt("book_id"));
                book.setAuthor(rs.getString("author"));
                book.setBook_title(rs.getString("book_title"));
                books.add(book);
            }


        } catch (SQLException e) {
            throw new DbConnectionError("Couldn't load data from the table Book");
        }
        //for orderItem

        try {
            stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from OrderItem");
            while (rs.next()) {
                OrderItem orderitem = new OrderItem();
                // inside the DB user_id is generated automatically,
                //  but we still need to load it into our program from the database
                orderitem.setOrder_id(rs.getInt("order_id"));
                orderitem.setProduct_id(rs.getInt("product_id"));
                orderitem.setCustomer_id(rs.getInt("customer_id"));
                orderitem.setCreated_at(rs.getDate("date"));
                orderitem.setQty(rs.getInt("quantity"));
                orderitem.setUnit_price(rs.getFloat("unit_price"));
                orderItems.add(orderitem);
            }


        } catch (SQLException e) {
            throw new DbConnectionError("Couldn't load data from the table OrderItem");
        }
    }


    // we create an exception class for database connection
    public static class DbConnectionError extends Exception {
        public DbConnectionError(String message) {
            super(message);
        }
    }

    // inside the constructor, create the connection to the database, and possibly throw a connection error :)
    // https://www.javatpoint.com/example-to-connect-to-the-mysql-database#:~:text=Connection%20URL%3A%20The%20connection%20URL,sonoo%20is%20the%20database%20name.
    public Database() throws DbConnectionError {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/final_project", "root", "34213421");

            // load all data from database
            loadDb();

        } catch (Exception e) {
            throw new DbConnectionError("The program can't connect to the Database. MySQL may not be installed.");
        }
    }

    public void createCustomer(Customer customer) {
        Statement stmt = null;
        try {
            stmt = this.conn.createStatement();
            int iRows = stmt.executeUpdate(
                    "insert into into table customer (" +
                            "username, password) " +
                            " values (" +
                            customer.getUsername() + "," + customer.getPassword() +
                            ")  * from customers");

            // using the code below, we check if the insert worked out,
            //  If it succeeded, we set the user id :D
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                customer.setUser_id(generatedKeys.getInt(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }

            // only after adding the customer to the database:
            customers.add(customer);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void updateCustomer(int ID, Customer updatedCustomer) {
        String query = "update customers set " +
                "username=" + updatedCustomer.getUsername() + "," +
                "password=" + updatedCustomer.getPassword() +
                "  where customer_id=" + ID;

        try {
            Statement stmt = conn.createStatement();
            int iRows = stmt.executeUpdate(query);
            if (iRows == 0) {
                throw new SQLException("No customer with ID=" + ID + " was updated");
            }
            stmt.close();

            for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getUser_id() == ID) {
                    customers.set(i, updatedCustomer);
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteCustomer(int ID) {
        try {
            Statement stmt = conn.createStatement();
            int iRows = stmt.executeUpdate("delete customers where customer_id=" + ID);
            if (iRows == 0) {
                throw new SQLException("No customer with ID=" + ID + " was deleted");
            }
            stmt.close();

           for (int i = 0; i < customers.size(); i++) {
                if (customers.get(i).getUser_id() == ID) {
                    customers.remove(i);
                    break;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}

