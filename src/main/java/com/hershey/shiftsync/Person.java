package com.hershey.shiftsync;

// person objects
public class Person {
    private String email;
    private String password;
    private String phoneNumber;


    // Constructors
    public Person(String email,String password) {
        this.email= email;
        this.password = password;
    }

    public Person(String email,String password, String phoneNumber) {
        this.email= email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }

    // getter methods
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhonenumber() {
        return phoneNumber;
    }

    // setter method
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhonenumber(String phonenumber) {
        this.phoneNumber = phonenumber;
    }
}
