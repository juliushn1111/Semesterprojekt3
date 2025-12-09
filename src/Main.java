import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewHandler;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        System.out.println("JavaFX starter nu...");

        ViewHandler viewHandler = new ViewHandler(stage);

        // ✅ VIGTIG RETTELSE:
        viewHandler.openMainView();

        stage.setTitle("Semesterprojekt");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
