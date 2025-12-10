package view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Information;
import model.Institution;
import model.Room;

public class InfoViewController {

    @FXML
    private ListView<String> infoListView;

    @FXML
    private TextField infoInputField;

    @FXML
    private TextField nameInputField;

    private ViewHandler viewHandler;
    private Institution institution;
    private Room currentRoom;

    private int selectedIndex = -1;

    //INIT
    public void init(ViewHandler handler) {
        this.viewHandler = handler;
        this.institution = handler.getInstitution();
        this.currentRoom = handler.getCurrentRoom();

        // Lytter på valg i listen, sørger for, at når brugeren klikker på et punkt i listen, så gemmes det
        infoListView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            selectedIndex = newVal.intValue();
            loadSelectedIntoFields();
        });

        updateInfoView();
    }

    // ---------- VISNING ----------
    private void updateInfoView() {
        infoListView.getItems().clear();

        if (currentRoom == null) return;

        for (Information info : currentRoom.getInformationList()) {
            infoListView.getItems().add(info.getName() + ": " + info.getInfo());
        }
    }

    // ---------- TILFØJ ----------
    @FXML
    private void handleAddInfo() {
        if (currentRoom == null) return;

        String text = infoInputField.getText().trim();
        String name = nameInputField.getText().trim();

        if (text.isEmpty()) return;
        if (name.isEmpty()) name = "Unkown";

        currentRoom.addInformation(text, name);

        infoInputField.clear();
        nameInputField.clear();

        updateInfoView();
        viewHandler.saveInstitution();
    }

    // ---------- SLET ----------
    @FXML
    private void handleDeleteInfo() {
        if (currentRoom == null || selectedIndex < 0) return;

        currentRoom.removeInformation(selectedIndex);

        infoInputField.clear();
        nameInputField.clear();
        selectedIndex = -1;

        updateInfoView();
        viewHandler.saveInstitution();
    }

    // ---------- REDIGÉR ----------
    @FXML
    private void handleEditInfo() {
        if (currentRoom == null || selectedIndex < 0) return;

        String newText = infoInputField.getText().trim();
        String newName = nameInputField.getText().trim();

        if (newText.isEmpty()) return;
        if (newName.isEmpty()) newName = "Unkown";

        Information info = currentRoom.getInformationList().get(selectedIndex);
        info.setInfo(newText);
        info.setName(newName);

        updateInfoView();
        viewHandler.saveInstitution();
    }

    private void loadSelectedIntoFields() {
        if (currentRoom == null || selectedIndex < 0) return;

        Information info = currentRoom.getInformationList().get(selectedIndex);
        infoInputField.setText(info.getInfo());
        nameInputField.setText(info.getName());
    }

    // ---------- TILBAGE ----------
    @FXML
    private void handleBackButton() {
        viewHandler.openMainView();
    }
}