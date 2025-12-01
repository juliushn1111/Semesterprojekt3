import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewHandler;

public class Main extends Application
{
    public void start(Stage primaryStage)
    {
        new ViewHandler(primaryStage);
    }
    public static void main(String[] args)
    {
        Application.launch(Main.class);
    }
}
