package com.amina.db;

import com.amina.entities.*;

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

    public void deleteCustomer(int id) {

    }
}
