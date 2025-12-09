package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;

public class SimpleGUIController {

    @FXML private ComboBox<String> roomComboBox;
    @FXML private ComboBox<String> weekdayComboBox;
    @FXML private TextField weekdayTextField;

    @FXML private Label mondayEventLabel;
    @FXML private Label tuesdayEventLabel;
    @FXML private Label wednesdayEventLabel;
    @FXML private Label thursdayEventLabel;
    @FXML private Label fridayEventLabel;

    @FXML private Button childrenTitleButton;
    @FXML private ListView<String> childrenListView;
    @FXML private ListView<String> staffListView;
    @FXML private ListView<String> agendaListView;

    private ViewHandler viewHandler;
    private Institution institution;
    private Room currentRoom;

    public void init(ViewHandler handler) {
        this.viewHandler = handler;
        this.institution = handler.getInstitution();
        this.currentRoom = handler.getCurrentRoom();  // ✅ HENTER DEN AKTUELLE STUE

        // ✅ Kun hvis der IKKE findes nogen stue endnu
        if (institution.getRooms().length == 0) {
            institution.addRoom("Rød stue");
            handler.saveInstitution();
            handler.setCurrentRoom(institution.getRooms()[0]);
            currentRoom = institution.getRooms()[0];
        }

        // ✅ Hvis currentRoom ikke er sat endnu (første opstart)
        if (currentRoom == null) {
            currentRoom = institution.getRooms()[0];
            handler.setCurrentRoom(currentRoom);
        }

        initRoomComboBox();
        initWeekdayComboBox();
        updateRoomView();
        updateAgenda();
        updateWeekView();
    }


    // ===== INITIALISÉR STUE-DROPDOWN =====
    private void initRoomComboBox() {
        roomComboBox.getItems().clear();

        // Fyld alle stuenavne ind
        for (Room r : institution.getRooms()) {
            roomComboBox.getItems().add(r.getName());
        }

        // ✅ Vælg den AKTUELLE stue i dropdown
        if (currentRoom != null) {
            roomComboBox.getSelectionModel().select(currentRoom.getName());
        } else if (!roomComboBox.getItems().isEmpty()) {
            // Fallback ved første opstart
            roomComboBox.getSelectionModel().select(0);
        }
    }

    private void initWeekdayComboBox() {
        weekdayComboBox.getItems().setAll("Mandag", "Tirsdag", "Onsdag", "Torsdag", "Fredag");
        weekdayComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void handleRoomChange() {
        String selected = roomComboBox.getValue();
        if (selected == null) return;

        for (Room r : institution.getRooms()) {
            if (r.getName().equals(selected)) {
                currentRoom = r;
                viewHandler.setCurrentRoom(r);
            }
        }

        updateRoomView();
        updateAgenda();
        updateWeekView();
    }

    @FXML
    private void handleAddRoom() {
        TextInputDialog d = new TextInputDialog();
        d.setHeaderText("Ny stue:");
        d.showAndWait().ifPresent(name -> {
            institution.addRoom(name);
            viewHandler.saveInstitution();
            initRoomComboBox();
        });
    }

    @FXML
    private void handleDeleteRoom() {
        if (institution.getRooms().length <= 1) return;
        institution.removeRoom(currentRoom.getName());
        currentRoom = institution.getRooms()[0];
        viewHandler.setCurrentRoom(currentRoom);
        viewHandler.saveInstitution();
        initRoomComboBox();
        updateRoomView();
        updateAgenda();
        updateWeekView();
    }

    @FXML
    private void handleSaveWeekday() {
        int i = weekdayComboBox.getSelectionModel().getSelectedIndex();
        currentRoom.setWeekEvent(i, weekdayTextField.getText());
        viewHandler.saveInstitution();
        updateWeekView();
    }

    @FXML
    private void handleChildrenButton() {
        viewHandler.openChildrenView();
    }

    private void updateWeekView() {
        Calender[] w = currentRoom.getWeekPlan();
        mondayEventLabel.setText(w[0].getEvent());
        tuesdayEventLabel.setText(w[1].getEvent());
        wednesdayEventLabel.setText(w[2].getEvent());
        thursdayEventLabel.setText(w[3].getEvent());
        fridayEventLabel.setText(w[4].getEvent());
    }

    private void updateRoomView() {
        if (currentRoom == null) return;

        // ===== BØRNEOVERSIGT (kun valgt stue) =====
        childrenTitleButton.setText("Børneoversigt: " + currentRoom.getName());

        childrenListView.getItems().clear();
        for (Child c : currentRoom.getChildren()) {
            childrenListView.getItems().add(c.getName() + " (" + c.getAge() + " år)");
        }

        // ===== ✅ BEMANDING (ALLE STUER PÅ ÉN GANG) =====
        staffListView.getItems().clear();

        for (Room room : institution.getRooms()) {
            for (Person p : room.getPersons()) {
                staffListView.getItems().add(room.getName() + ": " + p.getName());
            }
        }
    }

    private void updateAgenda() {
        agendaListView.getItems().clear();
        currentRoom.getAgendaList().forEach(a ->
                agendaListView.getItems().add(a.getTime() + " - " + a.getEntry()));
    }

    @FXML private void handleAgendaButton()  { viewHandler.openAgendaView(); }
    @FXML private void handleInfoButton()    { viewHandler.openInfoView(); }
    @FXML private void handleWeatherButton() { viewHandler.openWeatherView(); }
    @FXML private void handleCriticalButton(){ viewHandler.openCriticalView(); }
    @FXML private void handleStaffButton()   { viewHandler.openStaffView(); }
}
