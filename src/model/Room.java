package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {

    private String name;

    // Flere informationer
    private ArrayList<Information> informationList;

    // Enkelt agenda
    private Agenda agenda;

    // Enkelt kalender
    private Calender calender;

    // Flere personer
    private ArrayList<Person> persons;
    // Liste af børn
    private ArrayList<Child> children;

    public Room(String name) {
        this.name = name;
        this.informationList = new ArrayList<>();
        this.persons = new ArrayList<>();
        this.children = new ArrayList<>();
        this.agenda = null;
        this.calender = null;
    }

    // ---------- PERSON ----------
    public void addPerson(String name) {
        persons.add(new Person(name));
    }

    public void removePerson(String name) {
        persons.removeIf(p -> p.getName().equals(name));
    }

    public Person[] getPersons() {
        return persons.toArray(new Person[0]);
    }

    // ---------- INFORMATION ----------
    public void addInformation(String info, String name) {
        informationList.add(new Information(info, name));
    }

    public void removeInformation(String info) {
        informationList.removeIf(i -> i.getInfo().equals(info));
    }

    public Information[] getInformation() {
        return informationList.toArray(new Information[0]);
    }

    // ---------- AGENDA ----------
    public void setAgenda(int time, String entry) {
        this.agenda = new Agenda(time, entry);
    }

    public Agenda getAgenda() {
        return this.agenda;
    }

    // ---------- CALENDER ----------
    public void setCalender(String day, String event) {
        this.calender = new Calender(day, event);
    }

    public Calender getCalender() {
        return this.calender;
    }
    // ---------- CHILD ----------
    public void addChild(Child child) {
        children.add(child);
    }

    public void removeChild(String name) {
        children.removeIf(c -> c.getName().equals(name));
    }

    public Child[] getChildren() {
        return children.toArray(new Child[0]);
    }

    // ---------- TOSTRING ----------
    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", informationList=" + informationList +
                ", agenda=" + agenda +
                ", calender=" + calender +
                ", persons=" + persons +
                '}';
    }

    public String getName() {
        return name;
    }
}
