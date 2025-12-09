package view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Agenda;
import model.Room;

public class AgendaViewController {

    @FXML
    private ListView<String> agendaListView;

    @FXML
    private TextField timeInputField;

    @FXML
    private TextField entryInputField;

    private ViewHandler viewHandler;
    private Room currentRoom;

    private int selectedIndex = -1;

    // -------- INITIALISERING --------
    public void init(ViewHandler handler, Room room) {
        this.viewHandler = handler;
        this.currentRoom = room;

        // Når brugeren klikker på et punkt i listen
        agendaListView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            selectedIndex = newVal.intValue();
            loadSelectedIntoFields();
        });

        updateAgendaView();
    }

    // -------- VISNING --------
    private void updateAgendaView() {
        agendaListView.getItems().clear();

        for (Agenda a : currentRoom.getAgendaList()) {
            agendaListView.getItems().add(a.getTime() + " - " + a.getEntry());
        }
    }

    // -------- TILFØJ --------
    @FXML
    private void handleAddAgenda() {
        String time = timeInputField.getText().trim();
        String entry = entryInputField.getText().trim();

        if (time.isEmpty() || entry.isEmpty()) return;

        currentRoom.addAgenda(time, entry);

        viewHandler.saveInstitution();

        timeInputField.clear();
        entryInputField.clear();

        updateAgendaView();
    }

    // -------- REDIGÉR --------
    @FXML
    private void handleEditAgenda() {
        if (selectedIndex < 0) return;

        String newTime = timeInputField.getText().trim();
        String newEntry = entryInputField.getText().trim();

        if (newTime.isEmpty() || newEntry.isEmpty()) return;

        Agenda a = currentRoom.getAgendaList().get(selectedIndex);
        a.setTime(newTime);
        a.setEntry(newEntry);

        viewHandler.saveInstitution();

        updateAgendaView();
    }

    // -------- SLET --------
    @FXML
    private void handleDeleteAgenda() {
        if (selectedIndex < 0) return;

        currentRoom.removeAgenda(selectedIndex);

        selectedIndex = -1;
        timeInputField.clear();
        entryInputField.clear();

        viewHandler.saveInstitution();

        updateAgendaView();
    }

    // -------- HJÆLP --------
    private void loadSelectedIntoFields() {
        if (selectedIndex < 0) return;

        Agenda a = currentRoom.getAgendaList().get(selectedIndex);

        timeInputField.setText(a.getTime());
        entryInputField.setText(a.getEntry());
    }

    // -------- TILBAGE --------
    @FXML
    private void handleBackButton() {
        viewHandler.openMainView();
    }
}