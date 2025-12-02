package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class SimpleGUIController {

    @FXML
    private TextField textField;

    @FXML
    private Button button;

    @FXML
    private Label roomTitleLabel;   // <-- tilføjet

    private ViewHandler viewHandler;

    public void init(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }

    @FXML
    private void handleButtonClick(ActionEvent event) {
        System.out.println("Knappen blev klikket!");

        if (textField != null) {
            textField.setText("Hej fra GUI 🤖");
        }

        if (roomTitleLabel != null) {
            roomTitleLabel.setText("Room A");
        }
    }
}
