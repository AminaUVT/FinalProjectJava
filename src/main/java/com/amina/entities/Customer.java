package com.amina.entities;

public class Customer extends User {

    public Customer(String username, String password) {
        super(0, username, password);
    }

    public Customer() {

    }
    @Override
    public String toString() {
        return String.format("Customer(" +
                "ID=%s," +
                "username=%s," +
                "password=%s" +
                ")", user_id, username, password);
    }
}
