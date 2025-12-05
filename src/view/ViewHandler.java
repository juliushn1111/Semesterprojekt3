package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Institution;
import persistence.ModelManager;

import java.io.IOException;

public class ViewHandler {

    private Stage primaryStage;

    // Hoved-view (SimpleGUI)
    private Scene simpleGUIScene;
    private SimpleGUIController simpleGUIController;

    // Vejr-view
    private Scene weatherScene;
    private WeatherViewController weatherController;

    // Info-view
    private Scene infoScene;
    private InfoViewController infoController;

    // Critical-view
    private Scene criticalScene;
    private CriticalViewController criticalController;

    // ====== MODEL + PERSISTENCE ======
    private Institution institution;
    private ModelManager modelManager;

    public ViewHandler(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Børnehuset Solstrålen");

        // Opret ModelManager og forsøg at loade data fra fil
        modelManager = new ModelManager("institutionData.bin");
        institution = modelManager.load();

        // Hvis der ikke fandtes en fil, laver vi en ny tom institution
        if (institution == null) {
            institution = new Institution("Børnehuset Solstrålen");
        }

        // Start på hovedskærmen
        openMainView();
    }

    // Giver controllers adgang til modellen
    public Institution getInstitution() {
        return institution;
    }

    // Kaldes fra controllers når der er ændret i modellen
    public void saveInstitution() {
        modelManager.save(institution);
    }

    // ==========================
    //  MAIN VIEW
    // ==========================
    public void openMainView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SimpleGUI.fxml"));

            simpleGUIScene = new Scene(loader.load());
            simpleGUIController = loader.getController();
            simpleGUIController.init(this);   // giver ViewHandler videre

            primaryStage.setScene(simpleGUIScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Failed to load SimpleGUI.fxml");
            e.printStackTrace();
        }
    }

    // ==========================
    //  WEATHER VIEW
    // ==========================
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

    // ==========================
    //  INFO VIEW
    // ==========================
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

    // ==========================
    //  CRITICAL VIEW
    // ==========================
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