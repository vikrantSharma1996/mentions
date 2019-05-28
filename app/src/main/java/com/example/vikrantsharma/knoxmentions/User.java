package com.example.vikrantsharma.knoxmentions;

import java.util.Arrays;
import java.util.List;


public class User {

    private String fullname;
    private String username;
    private int userid;

    public User(String fullname, String username ,int userid) {
        this.fullname = fullname;
        this.username = username;
        this.userid=userid;
    }



    public String getFullname() {
        return fullname;
    }

    public String getUsername() {
        return username;
    }

    public int getUserid() {
        return userid;
    }

    public static List<User> USERS = Arrays.asList(

            new User("Chang Min Ki", "Chang_Min_Ki", 101),
            new User("Ha Eun", "Ha_Eun", 102),
            new User("Joo Won", "Joo_Won", 103),
            new User("Sam Son", "Sam_Son", 104),
            new User("Jacob Musk", "Jacob_Musk", 105)

    );
}
