package com.grupo3.confido.model;

public class User {
    /*======== Variables ========*/
    public String firstName;
    public String lastName;
    public String email;
    public String nickName;

    /*======== Empty Constructor  ========*/
    public User() {}

    /*======== Constructor ========*/
    public User(String firstName, String lastName, String email, String nickName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.nickName=nickName;
    }


}