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

    // 🔹 Info-view
    private Scene infoScene;
    private InfoViewController infoController;

    // Critical-View
    private Scene criticalScene;
    private CriticalViewController criticalController;

    public ViewHandler(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Børnehuset Solstrålen");

        try {
            FXMLLoader loader = new FXMLLoader();
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

    // tilbage til hoved-skærmen
    public void openMainView() {
        primaryStage.setScene(simpleGUIScene);
        primaryStage.show();
    }

    // Vejr-view
    public void openWeatherView() {
        try {
            FXMLLoader loader = new FXMLLoader();
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

    // 🔹 Info-view
    public void openInfoView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("InfoView.fxml"));

            infoScene = new Scene(loader.load());
            infoController = loader.getController();
            infoController.init(this);

            primaryStage.setScene(infoScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Failed to load InfoView.fxml");
            e.printStackTrace();
        }
    }

    // 🔹Critical
    public void openCriticalView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CriticalView.fxml"));

            criticalScene = new Scene(loader.load());
            criticalController = loader.getController();
            criticalController.init(this);

            primaryStage.setScene(criticalScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Failed to load CriticalView.fxml");
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}