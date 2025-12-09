package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import model.*;

public class SimpleGUIController {

    // ===== FXML =====
    @FXML private FlowPane roomButtonPane;
    @FXML private TextField newRoomField;
    @FXML private Label roomTitleLabel;
    @FXML private ListView<String> childrenListView;
    @FXML private ListView<String> staffListView;
    @FXML private ListView<String> agendaListView;

    // ===== MODEL =====
    private Institution institution;
    private Room currentRoom;
    private ViewHandler viewHandler;

    public void init(ViewHandler handler) {
        this.viewHandler = handler;
        this.institution = handler.getInstitution();
        this.currentRoom = handler.getCurrentRoom();

        if (institution.getRooms().length == 0) {
            institution.addRoom("Rød stue");
            handler.saveInstitution();
        }

        if (currentRoom == null) {
            currentRoom = institution.getRooms()[0];
            handler.setCurrentRoom(currentRoom);
        }

        buildRoomButtons();
        updateRoomView();
        updateAgenda();
    }

    // ===== STUE-KNAPPER =====
    private void buildRoomButtons() {
        roomButtonPane.getChildren().clear();

        for (Room r : institution.getRooms()) {
            Button btn = new Button(r.getName());
            btn.setPrefWidth(160);
            btn.setPrefHeight(55);
            btn.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: lightgray;");

            btn.setOnAction(e -> {
                currentRoom = r;
                viewHandler.setCurrentRoom(r);
                highlightActive(btn);
                updateRoomView();
                updateAgenda();
            });

            roomButtonPane.getChildren().add(btn);
        }

        if (!roomButtonPane.getChildren().isEmpty()) {
            highlightActive((Button) roomButtonPane.getChildren().get(0));
        }
    }

    private void highlightActive(Button active) {
        for (var n : roomButtonPane.getChildren()) {
            n.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: lightgray;");
        }
        active.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-background-color: lightgreen;");
    }

    // ===== CRUD STUER =====
    @FXML
    private void handleAddRoom() {
        String name = newRoomField.getText().trim();
        if (name.isEmpty()) return;

        institution.addRoom(name);
        viewHandler.saveInstitution();

        newRoomField.clear();
        buildRoomButtons();
    }

    @FXML
    private void handleDeleteRoom() {
        if (currentRoom == null) return;

        institution.removeRoom(currentRoom);
        currentRoom = institution.getRooms().length > 0 ? institution.getRooms()[0] : null;
        viewHandler.setCurrentRoom(currentRoom);

        viewHandler.saveInstitution();
        buildRoomButtons();

        if (currentRoom != null) {
            updateRoomView();
            updateAgenda();
        }
    }

    @FXML
    private void handleRenameRoom() {
        if (currentRoom == null) return;

        String newName = newRoomField.getText().trim();
        if (newName.isEmpty()) return;

        currentRoom.setName(newName);
        viewHandler.saveInstitution();

        newRoomField.clear();
        buildRoomButtons();
        updateRoomView();
    }

    // ===== OPDATERING =====
    private void updateRoomView() {
        roomTitleLabel.setText("Børneoversigt: " + currentRoom.getName());

        childrenListView.getItems().clear();
        for (Child c : currentRoom.getChildren()) {
            childrenListView.getItems().add(c.getName() + " (" + c.getAge() + " år)");
        }

        staffListView.getItems().clear();
        for (Person p : currentRoom.getPersons()) {
            staffListView.getItems().add(p.getName());
        }
    }

    private void updateAgenda() {
        agendaListView.getItems().clear();
        if (currentRoom.getAgendaList() != null) {
            currentRoom.getAgendaList().forEach(a ->
                    agendaListView.getItems().add(a.getTime() + " - " + a.getEntry())
            );
        }
    }

    // ===== NAVIGATION =====
    @FXML private void handleAgendaButton() { viewHandler.openAgendaView(); }
    @FXML private void handleInfoButton() { viewHandler.openInfoView(); }
    @FXML private void handleWeatherButton() { viewHandler.openWeatherView(); }
    @FXML private void handleCriticalButton() { viewHandler.openCriticalView(); }
}