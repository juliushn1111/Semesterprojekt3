package model;

public class Agenda {
    private int time;
    private String entry;

    public Agenda(int time, String entry){
        this.time = time;
        this.entry = entry;
    }

    public int getTime() {
        return time;
    }

    public String getEntry() {
        return entry;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public String toString() {
        return "Time: " + time + ":00 -> What to do: " + entry + ".";
    }
}

