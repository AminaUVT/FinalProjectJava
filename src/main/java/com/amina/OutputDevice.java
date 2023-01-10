package com.amina;

import com.amina.entities.Entity;

import java.util.List;

public class OutputDevice {

    void showNumberedEntities(List<? extends Entity> l) {
        for (int i = 0; i < l.size(); i++) {
            System.out.println((i+1) + ". " + l.get(i).toString());
        }
    }
}
