package com.example.vikrantsharma.knoxmentions.model;

import java.util.Arrays;
import java.util.List;


public class User {

    private  String imageurl;
    private String fullname;
    private String username;
    private int userid;

    public User(String imageurl, String fullname, String username, int userid) {
        this.imageurl = imageurl;
        this.fullname = fullname;
        this.username = username;
        this.userid = userid;
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

    public String getImageurl() {
        return imageurl;
    }

    public static List<User> USERS = Arrays.asList(

            new User("https://homepages.cae.wisc.edu/~ece533/images/airplane.png","Chang Min Ki", "Chang_Min_Ki", 101),
            new User("https://homepages.cae.wisc.edu/~ece533/images/airplane.png","Ha Eun", "Ha_Eun", 102),
            new User("https://homepages.cae.wisc.edu/~ece533/images/airplane.png","Joo Won", "Joo_Won", 103),
            new User("https://homepages.cae.wisc.edu/~ece533/images/airplane.png","Sam Son", "Sam_Son", 104),
            new User("https://homepages.cae.wisc.edu/~ece533/images/airplane.png","Jacob Musk", "Jacob_Musk", 105),
            new User("https://homepages.cae.wisc.edu/~ece533/images/airplane.png","Kim Lee","Kim_Lee",106)

    );
}
