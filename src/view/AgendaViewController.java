package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Agenda;
import model.Room;

public class AgendaViewController {

    @FXML private TextField timeField;
    @FXML private TextField entryInputField;
    @FXML private ListView<String> agendaListView;

    private ViewHandler viewHandler;
    private Room currentRoom;

    public void init(ViewHandler handler) {
        this.viewHandler = handler;
        this.currentRoom = handler.getCurrentRoom();
        updateAgenda();
    }

    @FXML
    private void handleAddAgenda() {
        String time = timeField.getText().trim();
        String entry = entryInputField.getText().trim();

        if (time.isEmpty() || entry.isEmpty()) return;

        // ✅ VIGTIG FIX: vi sender STRING – ikke Agenda-objekt
        currentRoom.addAgenda(time, entry);
        viewHandler.saveInstitution();

        timeField.clear();
        entryInputField.clear();

        updateAgenda();
    }

    private void updateAgenda() {
        agendaListView.getItems().clear();
        for (Agenda a : currentRoom.getAgendaList()) {
            agendaListView.getItems().add(a.getTime() + " - " + a.getEntry());
        }
    }

    @FXML
    private void handleDeleteAgenda() {
        int index = agendaListView.getSelectionModel().getSelectedIndex();

        if (index < 0) return;

        currentRoom.removeAgenda(index);
        viewHandler.saveInstitution();
        updateAgenda();
    }


    @FXML
    private void handleBackButton() {
        viewHandler.openMainView();
    }
}
