import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Loader FXML-filen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main.fxml"));
        Parent root = loader.load();

        // Sætter titel og scene
        primaryStage.setTitle("Mit JavaFX Program");
        primaryStage.setScene(new Scene(root, 600, 400)); // width:600, height:400
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Starter JavaFX-applikationen
        launch(args);
    }
}