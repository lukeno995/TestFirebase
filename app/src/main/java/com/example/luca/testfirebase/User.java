package com.example.luca.testfirebase;

/**
 * Created by Luca on 13/02/2018.
 */

public class User {
    private String id;
    private String progetto;

    public User(String id, String progetto) {
        this.id = id;
        this.progetto = progetto;
    }

    public String getId() {
        return id;
    }

    public String getProgetto() {
        return progetto;
    }
}
