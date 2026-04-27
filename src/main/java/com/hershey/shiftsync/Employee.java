package com.hershey.shiftsync;

import java.util.ArrayList;
import java.util.List;

public class Employee extends Person {
    private List<Shift> shifts = new ArrayList<>();

    public Employee(String email, String password, String phoneNumber) {
        super(email, password, phoneNumber);
    }

    public void addShift(Shift shift) {
        shifts.add(shift);
    }

    public void viewShifts() {
        for (Shift s : shifts) {
            System.out.println(s);
        }
    }
}