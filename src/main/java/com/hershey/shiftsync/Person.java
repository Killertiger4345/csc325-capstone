package com.hershey.shiftsync;

// person objects
public class Person {
    private String email;
    private String password;
    private String phonenumber;


    // Constructor
    public Person(String email,String password, String phonenumber) {
        this.email= email;
        this.password = password;
        this.phonenumber = phonenumber;
    }


    // getter methods
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    // setter method


    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

}
