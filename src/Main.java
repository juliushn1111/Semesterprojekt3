import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewHandler;

import model.Institution;
import model.Room;
import model.Child;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        System.out.println("Backend test kører12345");

        // Backend test-objekter
        Institution institution = new Institution("Børnehuset Solstrålen");
        Room room = new Room("Rød stue");
        Child child = new Child("Oliver", "Hansen", 3);

        room.addChild(child);  // ← din eksisterende kode

        System.out.println(institution);
        System.out.println(room);
        System.out.println(child);

        // Start GUI
        new ViewHandler(primaryStage);
    }

    public static void main(String[] args) {
        launch(args); // starter JavaFX og kalder start()
    }
}
