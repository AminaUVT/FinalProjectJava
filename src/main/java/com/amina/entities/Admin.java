package com.amina.entities;

public class Admin extends User  {

    public Admin() {

    }

    public Admin(int user_id, String username, String password) {
        super(user_id, username, password);
    }

    @Override
    public String toString() {
        return String.format("Admin(" +
                "ID=%s," +
                "username=%s," +
                "password=%s" +
                ")", user_id, username, password);
    }
}
