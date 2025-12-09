package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;

public class StaffViewController {

    @FXML private TextField nameField;
    @FXML private ComboBox<String> roomComboBox;
    @FXML private ListView<String> staffListView;

    private ViewHandler viewHandler;
    private Institution institution;

    private int selectedIndex = -1;

    // ===== INIT =====
    public void init(ViewHandler handler) {
        this.viewHandler = handler;
        this.institution = handler.getInstitution();

        initRoomComboBox();
        updateStaffList();

        staffListView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            selectedIndex = newVal.intValue();
        });
    }

    // ===== FYLD STUER I DROPDOWN =====
    private void initRoomComboBox() {
        roomComboBox.getItems().clear();

        for (Room r : institution.getRooms()) {
            roomComboBox.getItems().add(r.getName());
        }

        if (!roomComboBox.getItems().isEmpty()) {
            roomComboBox.getSelectionModel().selectFirst();
        }
    }

    // ===== OPDATER PERSONALELISTE (ALLE STUER) =====
    private void updateStaffList() {
        staffListView.getItems().clear();

        for (Room r : institution.getRooms()) {
            for (Person p : r.getPersons()) {
                staffListView.getItems().add(r.getName() + ": " + p.getName());
            }
        }
    }

    // ===== TILFØJ PERSONALE =====
    @FXML
    private void handleAddStaff() {
        String name = nameField.getText().trim();
        String roomName = roomComboBox.getValue();

        if (name.isEmpty() || roomName == null) return;

        Room room = institution.findRoom(roomName);
        room.addPerson(name);

        viewHandler.saveInstitution();

        nameField.clear();
        updateStaffList();
    }

    // ===== SLET PERSONALE =====
    @FXML
    private void handleDeleteStaff() {
        if (selectedIndex < 0) return;

        String line = staffListView.getItems().get(selectedIndex);
        String[] parts = line.split(": ");

        String roomName = parts[0];
        String personName = parts[1];

        Room room = institution.findRoom(roomName);
        room.removePerson(personName);

        viewHandler.saveInstitution();

        selectedIndex = -1;
        updateStaffList();
    }

    // ===== TILBAGE =====
    @FXML
    private void handleBackButton() {
        viewHandler.openMainView();
    }
}
