package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Institution;
import model.Room;
import persistence.ModelManager;

import java.io.IOException;

public class ViewHandler {

    private Stage primaryStage;

    // Scener
    private Scene simpleGUIScene;
    private Scene weatherScene;
    private Scene infoScene;
    private Scene criticalScene;

    // Controllers
    private SimpleGUIController simpleGUIController;
    private WeatherViewController weatherViewController;
    private InfoViewController infoViewController;
    private CriticalViewController criticalViewController;

    // Model + persistence
    private Institution institution;
    private ModelManager modelManager;

    // Aktuelt valgt rum/stue
    private Room currentRoom;

    public ViewHandler(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Børnehuset Solstrålen");

        // Persistence: prøv at loade institutionen fra fil
        modelManager = new ModelManager("institutionData.bin");
        institution = modelManager.load();

        if (institution == null) {
            // Første gang programmet kører
            institution = new Institution("Børnehuset Solstrålen");
            // Sørg for mindst én stue
            institution.addRoom("Rød stue");
        }

        // Sæt currentRoom til første rum
        Room[] rooms = institution.getRooms();
        if (rooms.length > 0) {
            currentRoom = rooms[0];
        }

        openMainView();
    }

    // --------- MODEL-ACCESS ---------
    public Institution getInstitution() {
        return institution;
    }

    public void saveInstitution() {
        modelManager.save(institution);
    }

    // Aktuelt rum (bruges af SimpleGUI/InfoView)
    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room room) {
        this.currentRoom = room;
    }

    // --------- MAIN VIEW ---------
    public void openMainView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("SimpleGUI.fxml"));

            simpleGUIScene = new Scene(loader.load());
            simpleGUIController = loader.getController();
            simpleGUIController.init(this);

            primaryStage.setScene(simpleGUIScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Failed to load SimpleGUI.fxml");
            e.printStackTrace();
        }
    }

    // --------- WEATHER VIEW ---------
    public void openWeatherView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("WeatherView.fxml"));

            weatherScene = new Scene(loader.load());
            weatherViewController = loader.getController();
            weatherViewController.init(this);

            primaryStage.setScene(weatherScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Failed to load WeatherView.fxml");
            e.printStackTrace();
        }
    }

    // --------- INFO VIEW ---------
    public void openInfoView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("InfoView.fxml"));

            infoScene = new Scene(loader.load());
            infoViewController = loader.getController();
            infoViewController.init(this);

            primaryStage.setScene(infoScene);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println("Failed to load InfoView.fxml");
            e.printStackTrace();
        }
    }

    // --------- CRITICAL VIEW ---------
    public void openCriticalView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CriticalView.fxml"));

            criticalScene = new Scene(loader.load());
            criticalViewController = loader.getController();
            criticalViewController.init(this);

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


    public void openAgendaView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AgendaView.fxml"));
            Scene scene = new Scene(loader.load());

            AgendaViewController controller = loader.getController();
            controller.init(this, currentRoom);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}