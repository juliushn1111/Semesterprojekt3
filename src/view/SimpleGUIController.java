package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Child;
import model.Institution;
import model.Room;

public class SimpleGUIController {

    // ====== KOBLING TIL FXML ======

    @FXML
    private Label roomTitleLabel;

    @FXML
    private ListView<String> childrenListView;

    // Bemanding – én liste pr. stue
    @FXML
    private ListView<String> staffListView;           // Rød stue
    @FXML
    private ListView<String> blueRoomStaffListView;   // Blå stue
    @FXML
    private ListView<String> yellowRoomStaffListView; // Gul stue
    @FXML
    private ListView<String> greenRoomStaffListView;  // Grøn stue

    // Agenda (venstre side)
    @FXML
    private ListView<String> agendaListView;

    // ====== ANDRE FELTER ======

    private ViewHandler viewHandler;

    private Institution institution;
    private Room redRoom;
    private Room blueRoom;
    private Room yellowRoom;
    private Room greenRoom;

    private Room currentRoom; // bruges til børneoversigten

    // Bliver kaldt fra ViewHandler, når FXML er loadet
    public void init(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;

        // 1) Lav institutionen + rum
        institution = new Institution("Børnehuset Solstrålen");

        institution.addRoom("Rød stue");
        institution.addRoom("Blå stue");
        institution.addRoom("Gul stue");
        institution.addRoom("Grøn stue");

        Room[] rooms = institution.getRooms();
        redRoom   = rooms[0];
        blueRoom  = rooms[1];
        yellowRoom = rooms[2];
        greenRoom = rooms[3];

        // 2) Bemanding
        redRoom.addPerson("R. Karen");
        redRoom.addPerson("Fm. Frede");

        blueRoom.addPerson("Pæd. Anna");
        blueRoom.addPerson("Medhj. Jonas");

        yellowRoom.addPerson("Pæd. Maria");
        yellowRoom.addPerson("Medhj. Lars");

        greenRoom.addPerson("Pæd. Sofie");
        greenRoom.addPerson("Medhj. Emil");

        // 3) Børn i rød stue
        currentRoom = redRoom;
        currentRoom.addChild(new Child("Magne", "Dreng", 4));
        currentRoom.addChild(new Child("Carla", "Pige", 3));
        currentRoom.addChild(new Child("Sofia", "Pige", 5));

        // 4) Fyld GUI
        updateRoomView();
        updateStaffViews();
        updateAgenda();
    }

    // ====== OPDATERING AF VIEW ======

    // Titel + børneliste for currentRoom
    private void updateRoomView() {
        if (currentRoom == null) return;

        if (roomTitleLabel != null) {
            roomTitleLabel.setText("Børneoversigt: " + currentRoom.getName());
        }

        if (childrenListView != null) {
            childrenListView.getItems().clear();
            if (currentRoom.getChildren() != null) {
                for (Child c : currentRoom.getChildren()) {
                    if (c != null) {
                        childrenListView.getItems()
                                .add(c.getName() + " (" + c.getAge() + " år)");
                    }
                }
            }
        }
    }

    // Bemanding i alle 4 stuer
    private void updateStaffViews() {
        // Rød
        if (staffListView != null && redRoom != null && redRoom.getPersons() != null) {
            staffListView.getItems().clear();
            for (var p : redRoom.getPersons()) {
                if (p != null) {
                    staffListView.getItems().add(p.getName());
                }
            }
        }

        // Blå
        if (blueRoomStaffListView != null && blueRoom != null && blueRoom.getPersons() != null) {
            blueRoomStaffListView.getItems().clear();
            for (var p : blueRoom.getPersons()) {
                if (p != null) {
                    blueRoomStaffListView.getItems().add(p.getName());
                }
            }
        }

        // Gul
        if (yellowRoomStaffListView != null && yellowRoom != null && yellowRoom.getPersons() != null) {
            yellowRoomStaffListView.getItems().clear();
            for (var p : yellowRoom.getPersons()) {
                if (p != null) {
                    yellowRoomStaffListView.getItems().add(p.getName());
                }
            }
        }

        // Grøn
        if (greenRoomStaffListView != null && greenRoom != null && greenRoom.getPersons() != null) {
            greenRoomStaffListView.getItems().clear();
            for (var p : greenRoom.getPersons()) {
                if (p != null) {
                    greenRoomStaffListView.getItems().add(p.getName());
                }
            }
        }
    }

    // Dummy-data til agendaen
    private void updateAgenda() {
        if (agendaListView == null) return;

        agendaListView.getItems().clear();
        agendaListView.getItems().add("08:00 - Morgenleg");
        agendaListView.getItems().add("09:00 - Samling");
        agendaListView.getItems().add("10:00 - Ude på legepladsen");
        agendaListView.getItems().add("11:30 - Frokost");
        agendaListView.getItems().add("12:30 - Middagslur / stille leg");
    }

    // ====== EVENT-HANDLERS FRA FXML ======

    @FXML
    private void handleWeatherButton() {
        System.out.println(">>> handleWeatherButton CALLED");

        if (viewHandler != null) {
            viewHandler.openWeatherView();
        } else {
            System.out.println(">>> viewHandler is NULL");
        }
    }

    @FXML
    private void handleInfoButton() {
        if (viewHandler != null) {
            viewHandler.openInfoView();
        }
    }

    @FXML
    private void handleCriticalButton() {
        if (viewHandler != null) {
            viewHandler.openCriticalView();
        }
    }
}
