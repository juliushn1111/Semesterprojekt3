package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {

    private Stage primaryStage;
    private Scene simpleGUIScene;
    private SimpleGUIController simpleGUIController;

    public ViewHandler(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Børnehuset Solstrålen");

        try {
            FXMLLoader loader = new FXMLLoader();

            // FXML ligger i samme package som ViewHandler → kun filnavn
            System.out.println("FXML location: " + getClass().getResource("SimpleGUI.fxml"));
            loader.setLocation(getClass().getResource("SimpleGUI.fxml"));

            simpleGUIScene = new Scene(loader.load());
            simpleGUIController = loader.getController();
            simpleGUIController.init(this);

            this.primaryStage.setScene(simpleGUIScene);
            this.primaryStage.show();
        } catch (IOException e) {
            System.out.println("Failed to load SimpleGUI.fxml");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
