package model;

import java.io.Serializable;

public class Agenda implements Serializable {

    private String time;   // fx "08:00"
    private String entry;  // fx "Morgenleg"

    public Agenda(String time, String entry) {
        this.time = time;
        this.entry = entry;
    }

    public String getTime() {
        return time;
    }

    public String getEntry() {
        return entry;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    @Override
    public String toString() {
        return time + " - " + entry;
    }
}

