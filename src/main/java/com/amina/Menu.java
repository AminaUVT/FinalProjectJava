package com.amina;

import com.amina.db.Database;

public class Menu {

    protected Database db;

    protected InputDevice id;
    protected OutputDevice od;

    public Menu() {
        this.id = new InputDevice();
        this.od = new OutputDevice();
    }
}
