package com.example.aravind.viewchanger;

public class User {
    private String ID;
    private String Name;
    private String Password;
    private String Place;



    public User(String id, String uname, String pwod, String addr) {
        ID=id;
        Name = uname;
        Password = pwod;
        Place = addr;


    }

    public String getID() {
        return ID;
    }

    public String getName() {
        return Name;
    }

    public String getPassword() {
        return Password;
    }

    public String getPlace() {
        return Place;
    }
}