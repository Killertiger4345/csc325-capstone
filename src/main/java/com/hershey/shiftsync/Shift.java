package com.hershey.shiftsync;

public class Shift {
    private String date;
    private String time;

    public Shift(String date, String time) {
        this.date = date;
        this.time = time;
    }

    public String getDate() { return date; }
    public String getTime() { return time; }

    @Override
    public String toString() {
        return "Shift on " + date + " at " + time;
    }
}