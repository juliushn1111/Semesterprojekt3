package view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Institution;
import model.Person;
import model.Room;

public class StaffViewController {

    @FXML private ListView<String> staffListView;
    @FXML private TextField staffInputField;

    private ViewHandler viewHandler;
    private Institution institution;
    private Room currentRoom;

    private int selectedIndex = -1;

    public void init(ViewHandler handler) {
        this.viewHandler = handler;
        this.institution = handler.getInstitution();
        this.currentRoom = handler.getCurrentRoom();

        staffListView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            selectedIndex = newVal.intValue();
            loadSelectedIntoField();
        });

        updateStaffView();
    }

    private void updateStaffView() {
        staffListView.getItems().clear();

        if (currentRoom == null) return;

        for (Person p : currentRoom.getPersons()) {
            staffListView.getItems().add(p.getName());
        }
    }

    @FXML
    private void handleAddStaff() {
        if (currentRoom == null) return;

        String name = staffInputField.getText().trim();
        if (name.isEmpty()) return;

        currentRoom.addPerson(name);
        viewHandler.saveInstitution();

        staffInputField.clear();
        updateStaffView();
    }

    @FXML
    private void handleDeleteStaff() {
        if (currentRoom == null || selectedIndex < 0) return;

        String name = staffListView.getItems().get(selectedIndex);
        currentRoom.removePerson(name);
        viewHandler.saveInstitution();

        selectedIndex = -1;
        staffInputField.clear();
        updateStaffView();
    }

    @FXML
    private void handleEditStaff() {
        if (currentRoom == null || selectedIndex < 0) return;

        String newName = staffInputField.getText().trim();
        if (newName.isEmpty()) return;

        String oldName = staffListView.getItems().get(selectedIndex);
        currentRoom.removePerson(oldName);
        currentRoom.addPerson(newName);

        viewHandler.saveInstitution();
        staffInputField.clear();
        updateStaffView();
    }

    private void loadSelectedIntoField() {
        if (selectedIndex < 0) return;
        staffInputField.setText(staffListView.getItems().get(selectedIndex));
    }

    @FXML
    private void handleBackButton() {
        viewHandler.openMainView();
    }
}
