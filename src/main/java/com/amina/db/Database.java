package com.amina.db;

import com.amina.entities.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.jdbc.ScriptRunner;

public class Database {
    private static String DbConnectionUrl = "";

    public static void setDbConnectionUrl(String dbConnectionUrl) {
        DbConnectionUrl = dbConnectionUrl;
    }

    private List<Customer> customers;
    private List<Admin> admins;
    private List<OrderItem> orderItems;
    private List<Tea> teas;
    private List<Book> books;

    public static User login(String username, String password) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("packages named \"com.mysql.jdbc.Driver\" must be " +
                    "installed (e.g., inside pom.xml)");
        }

        try {

            Connection conn = DriverManager.getConnection(
                    Database.DbConnectionUrl
            );

            Statement stmt1 = conn.createStatement();
            Statement stmt2 = conn.createStatement();

            ResultSet customers = stmt1.executeQuery(
                    "select username, password from customers " +
                            "where username='" + username + "'");
            ResultSet admins = stmt2.executeQuery(
                    "select username, password from admins " +
                            "where username='" + username + "'");


            if (!customers.isBeforeFirst()) {
                // The result set is empty, so we don't have customers with the given name
            } else {
                customers.next();// username is unique, so we get to the first and only row in the result
                String customerPassword = customers.getString("password");
                if (password != null && password.equals(customerPassword)) {
                    Customer customer = new Customer();
                    customer.setUser_id(customers.getInt("user_id"));
                    customer.setPassword(customers.getString("password"));
                    customer.setUsername(customers.getString("username"));
                    return customer;
                }
            }


            if (!admins.isBeforeFirst()) {
                // The result set is empty, so we don't have admins with the given name
            } else {
                admins.next();// username is unique, so we get to the first and only row in the result
                String customerPassword = admins.getString("password");
                if (password != null && password.equals(customerPassword)) {
                    Admin admin = new Admin();
//                    admin.setUser_id(admins.getInt("user_id")); 
                    admin.setPassword(admins.getString("password"));
                    admin.setUsername(admins.getString("username"));
                    return admin;
                }
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // The database search returned no user with this username and password, so we return null.
        return null;

    }

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

    public static void createAndPopulateDb(String connUrl, String dbName) throws DbConnectionError, SQLException {
        // Create Db:
        try(Connection conn = DriverManager.getConnection(connUrl);
            Statement stmt = conn.createStatement();
        ) {
            String sql = String.format("CREATE DATABASE %s", dbName);
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Registering the Driver
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //Getting the connection
        String mysqlUrl = String.format("%s/%s", connUrl, dbName);
        Connection con = DriverManager.getConnection(mysqlUrl, "root", "password");
        System.out.println("Connection established......");
        //Initialize the script runner
        ScriptRunner sr = new ScriptRunner(con);
        //Creating a reader object
        Reader reader = null;
        try {

            String fileContents = Files.readString(Paths.get("book_and_tea.sql"))
                    .replace("final_project", dbName);
            // write to temp file
            PrintWriter printWriter = new PrintWriter( new FileWriter("book_and_tea_tmp.sql"));
            printWriter.print(fileContents);
            printWriter.close();
            // read from the temp file:
            reader = new BufferedReader(new FileReader("book_and_tea_tmp.sql"));
            //Running the script
            sr.runScript(reader);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadDb() throws DbConnectionError {
        admins = new ArrayList<>();
        customers = new ArrayList<>();
        teas = new ArrayList<>();
        books = new ArrayList<>();
        orderItems = new ArrayList<>();

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
            ResultSet rs = stmt.executeQuery("select * from teas");
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
            throw new DbConnectionError("Couldn't load data from the table Teas");
        }

        //for Book

        try {
            stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from books");
            while (rs.next()) {
                Book book = new Book();
                // inside the DB user_id is generated automatically,
                //  but we still need to load it into our program from the database
                book.setBook_id(rs.getInt("book_id"));
                book.setAuthor(rs.getString("author"));
                book.setTitle(rs.getString("title"));
                books.add(book);
            }


        } catch (SQLException e) {
            throw new DbConnectionError("Couldn't load data from the table Books");
        }
        //for orderItem

        try {
            stmt = this.conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from orderitems");
            while (rs.next()) {
                OrderItem orderitem = new OrderItem();
                // inside the DB user_id is generated automatically,
                //  but we still need to load it into our program from the database
                orderitem.setOrder_id(rs.getInt("order_id"));
                orderitem.setProduct_id(rs.getInt("product_id"));
                orderitem.setCustomer_id(rs.getInt("customer_id"));
                orderitem.setCreated_at(rs.getDate("created_at"));
                orderitem.setQuantity(rs.getInt("quantity"));
                orderitem.setUnit_price(rs.getFloat("unit_price"));
                orderItems.add(orderitem);
            }


        } catch (SQLException e) {
            throw new DbConnectionError("Couldn't load data from the table OrderItems");
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
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(
                    Database.DbConnectionUrl
            );

            // load all data from database
            loadDb();

        } catch (Exception e) {
            throw new DbConnectionError("The program can't connect to the Database. MySQL may not be installed.");
        }
    }

    public void createCustomer(Customer customer) {

        try {
            PreparedStatement stmt = conn.prepareStatement(
                    String.format(
                            "insert into " +
                                    " customers(username, password)" +
                                    " values  ('%s', '%s')",
                            customer.getUsername(), customer.getPassword()),
                    new String[]{"user_id"});

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            // using the code below, we check if the insert worked out,
            //  If it succeeded, we set the user id
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    System.out.println(rs.getInt(1));
                    customer.setUser_id(rs.getInt(1));
                }
                rs.close();
            }

            // only after adding the customer to the database:
            customers.add(customer);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void updateCustomer(int ID, Customer updatedCustomer) {
        String query = "update customers set " +
                "username='" + updatedCustomer.getUsername() + "'," +
                "password='" + updatedCustomer.getPassword() + "'" +
                "  where user_id=" + ID;

        try {
            Statement stmt = conn.createStatement();
            int iRows = stmt.executeUpdate(query);
            if (iRows == 0) {
                throw new SQLException("No customer with ID=" + ID + " was updated");
            }
            stmt.close();

            //  and also update the values stored inside RAM (the lists inside Database)
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
            int iRows = stmt.executeUpdate("delete from customers where user_id=" + ID);
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

