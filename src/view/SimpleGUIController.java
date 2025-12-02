package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Child;
import model.Institution;
import model.Room;

public class SimpleGUIController {

    // ====== KOBLING TIL FXML (fx:id) ======

    // fx:id="roomTitleLabel" i SceneBuilder
    @FXML
    private Label roomTitleLabel;

    // fx:id="childrenListView"
    @FXML
    private ListView<String> childrenListView;

    // fx:id="staffListView"
    @FXML
    private ListView<String> staffListView;

    // ====== ANDRE FELTER ======

    private ViewHandler viewHandler;

    // Model-objekter
    private Institution institution;
    private Room currentRoom;

    // Bliver kaldt fra ViewHandler, når FXML er loadet
    public void init(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;

        // 1) Lav institutionen
        institution = new Institution("Børnehuset Solstrålen");

        // Tilføj ét rum: Rød stue
        institution.addRoom("Rød stue");

        // Tag første rum i institutionens liste
        currentRoom = institution.getRooms()[0];

        // 2) Tilføj personale til rummet
        currentRoom.addPerson("R. Karen");
        currentRoom.addPerson("Fm. Frede");

        // 3) Tilføj nogle børn til rummet
        currentRoom.addChild(new Child("Magne", "Dreng", 4));
        currentRoom.addChild(new Child("Carla", "Pige", 3));
        currentRoom.addChild(new Child("Sofia", "Pige", 5));

        // 4) Fyld GUI med data
        updateRoomView();
    }

    // Opdaterer label + lister med data fra currentRoom
    private void updateRoomView() {
        // Titel-label
        if (roomTitleLabel != null && currentRoom != null) {
            roomTitleLabel.setText("Børneoversigt: " + currentRoom.getName());
        }

        // Børne-liste
        childrenListView.getItems().clear();
        for (Child c : currentRoom.getChildren()) {
            childrenListView
                    .getItems()
                    .add(c.getName() + " (" + c.getAge() + " år)");
        }

        // Personale-liste
        staffListView.getItems().clear();
        for (var p : currentRoom.getPersons()) {
            staffListView.getItems().add(p.getName());
        }
    }
}
