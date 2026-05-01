package com.hershey.shiftsync;

// person objects
public class Person {
    private String email;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;

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

    public Person(String email, String password, String phoneNumber, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
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

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

