package model;

import java.util.ArrayList;

public class Institution {

    private String name;
    private ArrayList<Room> rooms;

    public Institution(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
    }

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
                i--; // vigtigt for at ikke springe et rum over
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
        return null; // returner null, hvis rummet ikke findes
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

    public void setAgendaForRoom(String roomName, int time, String entry) {
        Room r = findRoom(roomName);
        if (r != null) {
            r.setAgenda(time, entry);
        }
    }

    public void setCalenderForRoom(String roomName, String day, String event) {
        Room r = findRoom(roomName);
        if (r != null) {
            r.setCalender(day, event);
        }
    }

    @Override
    public String toString() {
        return "Institution{name='" + name + "', antal rum=" + rooms.size() + "}";
    }
}
