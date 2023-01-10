package com.amina.menu;

import com.amina.entities.User;

public class CustomerMenu extends Menu {
    public CustomerMenu(User user) {
        super(user);
    }

    @Override
    public void showOptions() {
    }

    @Override
    public int chooseOption() {
        return 0;
    }

    @Override
    public void run() {
    }
}
