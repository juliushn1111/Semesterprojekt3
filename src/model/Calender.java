package model;

import java.io.Serializable;

public class Calender implements Serializable {
    private String day;
    private String event;

    public Calender(String day, String event){
        this.day = day;
        this.event = event;
    }

    public String getDay() {
        return day;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void remove(String event){
        if ( this.event != null && this.event.equals(event)){
            this.event = null;
            // Fjerner kun eventet, hvis navent stemmer overens.
        }
    }
    public String toString(){
        String evt = (event == null) ? "none" : event; // Da det er en null vil den bare udskrive null,
        //Men med denne metode udskriver den none, hvis der ingen event er.
        return "Day: " + day + "/ Event: " + event + ".";
    }
}
