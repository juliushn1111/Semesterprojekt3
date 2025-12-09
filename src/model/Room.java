package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable {

    private String name;

    // Informationer for denne stue
    private ArrayList<Information> informationList;

    // Agenda for denne stue (liste)
    private ArrayList<Agenda> agendaList;

    // ✅ UGEPLAN (Mandag–Fredag) PR. STUE
    private Calender[] weekPlan;

    // Personale-liste
    private ArrayList<Person> persons;

    // Liste af børn
    private ArrayList<Child> children;

    public Room(String name) {
        this.name = name;

        this.informationList = new ArrayList<>();
        this.agendaList = new ArrayList<>();
        this.persons = new ArrayList<>();
        this.children = new ArrayList<>();

        // ✅ INITIALISER UGEPLAN
        String[] days = {"Mandag", "Tirsdag", "Onsdag", "Torsdag", "Fredag"};
        weekPlan = new Calender[5];
        for (int i = 0; i < 5; i++) {
            weekPlan[i] = new Calender(days[i], "");
        }
    }

    // =========================================================
    //                      PERSONALE
    // =========================================================

    public void addPerson(String name) {
        persons.add(new Person(name));
    }

    public void removePerson(String name) {
        persons.removeIf(p -> p.getName().equals(name));
    }

    public Person[] getPersons() {
        return persons.toArray(new Person[0]);
    }

    // =========================================================
    //                      INFORMATION
    // =========================================================

    public void addInformation(String info, String creatorName) {
        informationList.add(new Information(info, creatorName));
    }

    public void removeInformation(String info) {
        informationList.removeIf(i -> i.getInfo().equals(info));
    }

    public Information[] getInformation() {
        return informationList.toArray(new Information[0]);
    }

    // Bruges af InfoViewController
    public ArrayList<Information> getInformationList() {
        return informationList;
    }

    // Bruges til sletning via liste-index
    public void removeInformation(int index) {
        if (index >= 0 && index < informationList.size()) {
            informationList.remove(index);
        }
    }

    // =========================================================
    //                          AGENDA
    // =========================================================

    public void addAgenda(String time, String entry) {
        agendaList.add(new Agenda(time, entry));
    }

    public void removeAgenda(int index) {
        if (index >= 0 && index < agendaList.size()) {
            agendaList.remove(index);
        }
    }

    public ArrayList<Agenda> getAgendaList() {
        return agendaList;
    }

    // =========================================================
    //                        UGEPLAN (NY)
    // =========================================================

    // ✅ Gammel metode – bruges af Institution (kompatibilitet)
    public void setCalender(String day, String event) {
        for (Calender c : weekPlan) {
            if (c.getDay().equalsIgnoreCase(day)) {
                c.setEvent(event);
                return;
            }
        }
    }

    public Calender[] getWeekPlan() {
        return weekPlan;
    }

    // Sæt aktivitet for en bestemt dag (0 = Mandag, 4 = Fredag)
    public void setWeekEvent(int index, String event) {
        if (index >= 0 && index < weekPlan.length) {
            weekPlan[index].setEvent(event);
        }
    }

    // =========================================================
    //                         CHILDREN
    // =========================================================

    public void addChild(Child child) {
        children.add(child);
    }

    public void removeChild(String name) {
        children.removeIf(c -> c.getName().equals(name));
    }

    public Child[] getChildren() {
        return children.toArray(new Child[0]);
    }

    // =========================================================
    //                        MISC
    // =========================================================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // =========================================================
    //                        TOSTRING
    // =========================================================

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", informationList=" + informationList +
                ", agendaList=" + agendaList +
                ", weekPlan=" + java.util.Arrays.toString(weekPlan) +
                ", persons=" + persons +
                ", children=" + children +
                '}';
    }
}