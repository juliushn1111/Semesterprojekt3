package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Institution implements Serializable {

    private String name;

    // RUM
    private ArrayList<Room> rooms;

    // CRITICAL INFO
    private ArrayList<CriticalInfo> criticalInfoList;

    public Institution(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.criticalInfoList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // =========================
    //        ROOMS
    // =========================

    // Tilføj et rum
    public void addRoom(String roomName) {
        Room newRoom = new Room(roomName);
        rooms.add(newRoom);
    }

    // Fjern et rum
    public void removeRoom(String roomName) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getName().equals(roomName)) {
                rooms.remove(i);
                i--; // så vi ikke springer et rum over
            }
        }
    }

    // Hent alle rum som et array
    public Room[] getRooms() {
        Room[] arr = new Room[rooms.size()];
        for (int i = 0; i < rooms.size(); i++) {
            arr[i] = rooms.get(i);
        }
        return arr;
    }

    // Hjælpemetode: find et rum
    private Room findRoom(String roomName) {
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getName().equals(roomName)) {
                return rooms.get(i);
            }
        }
        return null; // hvis rummet ikke findes
    }

    // ---------- VIDERE TIL ROOM ----------

    public void addPersonToRoom(String roomName, String personName) {
        Room r = findRoom(roomName);
        if (r != null) {
            r.addPerson(personName);
        }
    }

    public void addInformationToRoom(String roomName, String info, String creatorName) {
        Room r = findRoom(roomName);
        if (r != null) {
            r.addInformation(info, creatorName);
        }
    }

    public void addAgendaToRoom(String roomName, String time, String entry) {
        Room r = findRoom(roomName);
        if (r != null) {
            r.addAgenda(time, entry);
        }
    }

    public void setCalenderForRoom(String roomName, String day, String event) {
        Room r = findRoom(roomName);
        if (r != null) {
            r.setCalender(day, event);
        }
    }

    // =========================
    //    CRITICAL INFO
    // =========================

    public void addCriticalInfo(CriticalInfo info) {
        criticalInfoList.add(info);
    }

    public void removeCriticalInfo(int index) {
        if (index >= 0 && index < criticalInfoList.size()) {
            criticalInfoList.remove(index);
        }
    }

    public ArrayList<CriticalInfo> getCriticalInfoList() {
        return criticalInfoList;
    }

    @Override
    public String toString() {
        return "Institution{name='" + name + "', antal rum=" + rooms.size() + "}";
    }

    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    public void setName(String name) {
        this.name = name;
    }

}