package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SimpleGUIController {

    @FXML
    private void handleButtonClick(ActionEvent event) {
        System.out.println("Knappen blev klikket!");
    }
}