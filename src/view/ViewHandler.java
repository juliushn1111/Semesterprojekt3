package view;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewHandler {

    private Stage primaryStage;

    // Hoved-view (SimpleGUI)
    private Scene simpleGUIScene;
    private SimpleGUIController simpleGUIController;

    // Vejr-view
    private Scene weatherScene;
    private WeatherViewController weatherController;

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

    // Bruges af WeatherViewController til at gå tilbage til dashboardet
    public void openMainView() {
        primaryStage.setScene(simpleGUIScene);
        primaryStage.show();
    }

    // Bruges af SimpleGUIController når man trykker på vejrikonet
    public void openWeatherView() {
        try {
            FXMLLoader loader = new FXMLLoader();

            System.out.println("FXML location: " + getClass().getResource("WeatherView.fxml"));
            loader.setLocation(getClass().getResource("WeatherView.fxml"));

            weatherScene = new Scene(loader.load());
            weatherController = loader.getController();
            weatherController.init(this);

            primaryStage.setScene(weatherScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Failed to load WeatherView.fxml");
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
