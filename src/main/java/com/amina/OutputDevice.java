package com.amina;

import com.amina.entities.Entity;

import java.util.List;

public class OutputDevice {

    public void showNumberedEntities(List<? extends Entity> l, String tableName) {
        if (l.size() == 0) {
            System.out.println("The DB contains no customers.");
        } else {
            System.out.println("List of all " + tableName + ":");
            for (int i = 0; i < l.size(); i++) {
                System.out.println(i + ". " + l.get(i).toString());
            }
        }
    }

    public void printMessage(String s) {
        System.out.println(s);
    }
}
