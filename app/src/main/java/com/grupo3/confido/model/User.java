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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}