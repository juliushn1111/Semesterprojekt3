package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class ViewHandler
{ private Stage primaryStage;
    private Scene simpleGUIScene;
    public ViewHandler(Stage primaryStage)
    { this.primaryStage = primaryStage;
        this.primaryStage.setTitle("A Simple Window");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("SimpleGUI.fxml"));
        try
        { simpleGUIScene = new Scene(loader.load());
            this.primaryStage.setScene(simpleGUIScene);
        }
        catch (IOException e)
        { System.out.println("Failed to load SimpleGUI.fxml");
            System.exit(1);
        }
        primaryStage.show();
    }
}
