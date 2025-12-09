package view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Child;
import model.Room;

public class ChildrenViewController {

    @FXML private Label roomLabel;
    @FXML private TextField nameField;
    @FXML private TextField sexField;
    @FXML private TextField ageField;
    @FXML private ListView<String> childrenListView;

    private ViewHandler viewHandler;
    private Room currentRoom;

    // Bliver kaldt af ViewHandler.openView(...)
    public void init(ViewHandler handler) {
        this.viewHandler = handler;
        this.currentRoom = handler.getCurrentRoom();

        if (currentRoom != null) {
            roomLabel.setText("Børneoversigt – " + currentRoom.getName());
        }
        updateChildrenList();
    }

    // Opdater liste med alle børn på currentRoom
    private void updateChildrenList() {
        childrenListView.getItems().clear();
        if (currentRoom == null) return;

        for (Child c : currentRoom.getChildren()) {
            String line = c.getName() + " (" + c.getAge() + " år, " + c.getSex() + ")";
            childrenListView.getItems().add(line);
        }
    }

    @FXML
    private void handleAddChild() {
        if (currentRoom == null) return;

        String name = nameField.getText().trim();
        String sex  = sexField.getText().trim();
        String ageText = ageField.getText().trim();

        if (name.isEmpty() || sex.isEmpty() || ageText.isEmpty()) {
            return; // kunne vise alert, men vi holder det simpelt
        }

        int age;
        try {
            age = Integer.parseInt(ageText);
        } catch (NumberFormatException e) {
            // alder ikke et tal → vi ignorerer bare (man kan lave alert hvis man vil)
            return;
        }

        // Tilføj barn til modellen
        currentRoom.addChild(new Child(name, sex, age));

        // Gem institutionen
        viewHandler.saveInstitution();

        // Ryd felter
        nameField.clear();
        sexField.clear();
        ageField.clear();

        // Opdater visning
        updateChildrenList();
    }

    @FXML
    private void handleDeleteChild() {
        if (currentRoom == null) return;

        int index = childrenListView.getSelectionModel().getSelectedIndex();
        if (index < 0) return;

        // Find det valgte barn via index
        Child[] children = currentRoom.getChildren();
        if (index >= children.length) return;

        String nameToRemove = children[index].getName();
        currentRoom.removeChild(nameToRemove);

        viewHandler.saveInstitution();
        updateChildrenList();
    }

    @FXML
    private void handleBackButton() {
        viewHandler.openMainView();
    }
}
