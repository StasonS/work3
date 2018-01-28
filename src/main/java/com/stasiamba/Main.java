package com.stasiamba;

import com.stasiamba.action.Action;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        Action action = new Action();
        try {
            action.act();
        } catch (IOException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }

        System.exit(0);

    }
}
