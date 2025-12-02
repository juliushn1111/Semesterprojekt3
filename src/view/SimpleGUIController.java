package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

// TODO: når I kobler modellen på, kan I importere jeres Room-klasse:
// import model.Room;

public class SimpleGUIController {

    // ---------- Top / header ----------
    @FXML
    private Label roomTitleLabel; // fx:id="roomTitleLabel"

    // ---------- Tabs ----------
    @FXML
    private TabPane roomTabPane; // fx:id="roomTabPane"

    // ----- Information-tab -----
    @FXML
    private Tab informationTab; // fx:id="informationTab"

    @FXML
    private ListView<String> informationListView; // fx:id="informationListView"

    @FXML
    private TextField infoTextField; // fx:id="infoTextField"

    @FXML
    private TextField infoAuthorTextField; // fx:id="infoAuthorTextField"

    @FXML
    private Button addInfoButton; // fx:id="addInfoButton"

    @FXML
    private Button deleteInfoButton; // fx:id="deleteInfoButton"

    // ----- Agenda-tab -----
    @FXML
    private Tab agendaTab; // fx:id="agendaTab"

    @FXML
    private ListView<String> agendaListView; // fx:id="agendaListView"

    @FXML
    private Button addAgendaItemButton; // fx:id="addAgendaItemButton"

    @FXML
    private Button deleteAgendaItemButton; // fx:id="deleteAgendaItemButton"

    // ----- Calendar-tab -----
    @FXML
    private Tab calendarTab; // fx:id="calendarTab"

    @FXML
    private ListView<String> calendarListView; // fx:id="calendarListView"
    // (evt byt til TableView senere hvis I vil være fancy)

    // ----- Staff-tab -----
    @FXML
    private Tab staffTab; // fx:id="staffTab"

    @FXML
    private ListView<String> staffListView; // fx:id="staffListView"

    @FXML
    private TextField staffNameTextField; // fx:id="staffNameTextField"

    @FXML
    private Button addStaffButton; // fx:id="addStaffButton"

    @FXML
    private Button removeStaffButton; // fx:id="removeStaffButton"

    // ---------- Infrastruktur ----------
    private ViewHandler viewHandler;
    // private Room room; // når I kobler modellen på

    // Kaldes fra ViewHandler når viewet oprettes
    public void init(ViewHandler viewHandler /*, Room room */) {
        this.viewHandler = viewHandler;
        // this.room = room;

        // TODO: når Room findes:
        // roomTitleLabel.setText(room.getName());
        // refreshInformation();
        // refreshAgenda();
        // refreshCalendar();
        // refreshStaff();
    }

    // ---------- Event handlers (SceneBuilder -> onAction) ----------

    @FXML
    private void handleAddInfo(ActionEvent event) {
        String text = infoTextField.getText();
        String author = infoAuthorTextField.getText();

        if (text == null || text.isBlank()) return;

        // Midlertidig “fake” implementation:
        String line = (author == null || author.isBlank())
            ? text
            : text + " (" + author + ")";
        informationListView.getItems().add(line);

        infoTextField.clear();
        infoAuthorTextField.clear();

        System.out.println("INFO added: " + line);
        // TODO: her skal I kalde room.addInformation(...) når modellen er klar
    }

    @FXML
    private void handleDeleteInfo(ActionEvent event) {
        int index = informationListView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            String removed = informationListView.getItems().remove(index);
            System.out.println("INFO removed: " + removed);
            // TODO: her skal I også fjerne fra room-modellen
        }
    }

    @FXML
    private void handleAddAgendaItem(ActionEvent event) {
        // TODO: skift til et rigtigt input (dialog eller tekstfelt)
        informationListView.getItems().add("New agenda item (TODO)");
        System.out.println("Agenda item added (placeholder)");
    }

    @FXML
    private void handleDeleteAgendaItem(ActionEvent event) {
        int index = agendaListView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            String removed = agendaListView.getItems().remove(index);
            System.out.println("Agenda item removed: " + removed);
        }
    }

    @FXML
    private void handleAddStaff(ActionEvent event) {
        String name = staffNameTextField.getText();
        if (name == null || name.isBlank()) return;

        staffListView.getItems().add(name);
        staffNameTextField.clear();

        System.out.println("Staff added: " + name);
        // TODO: room.addPerson(name);
    }

    @FXML
    private void handleRemoveStaff(ActionEvent event) {
        int index = staffListView.getSelectionModel().getSelectedIndex();
        if (index >= 0) {
            String removed = staffListView.getItems().remove(index);
            System.out.println("Staff removed: " + removed);
            // TODO: fjern også i Room
        }
    }

    // ---------- Helper-metoder (kan udbygges når model er klar) ----------

    private void refreshInformation() {
        // TODO: load fra room.getInformationList()
    }

    private void refreshAgenda() {
        // TODO: load fra room.getAgenda()
    }

    private void refreshCalendar() {
        // TODO: load fra room.getCalender()
    }

    private void refreshStaff() {
        // TODO: load fra room.getPersons()
    }
}
