package com.hershey.shiftsync;

public class Manager extends Person {

    public Manager(String email, String password, String phoneNumber, String firstName, String lastName) {
        super(email, password, phoneNumber, firstName, lastName);
    }

    public Manager(String email, String password, String phoneNumber) {
        super(email, password, phoneNumber);
    }

    public Shift createShift(String date, String time) {
        return new Shift(date, time);
    }

    public void assignShift(Employee emp, Shift shift) {
        emp.addShift(shift);
    }
}