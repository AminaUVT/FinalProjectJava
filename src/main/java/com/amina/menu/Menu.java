package com.amina.menu;

import com.amina.InputDevice;
import com.amina.OutputDevice;
import com.amina.db.Database;
import com.amina.entities.User;

public abstract class Menu {
    public Menu() {
    }

    protected Database db;

    protected InputDevice id;
    protected OutputDevice od;

    protected User user;

    public Menu(User user) {
        this.id = new InputDevice();
        this.od = new OutputDevice();
        this.user = user;
    }

    public abstract void showOptions() ;
    public abstract int chooseOption() ;
    public abstract void run() ;
}
