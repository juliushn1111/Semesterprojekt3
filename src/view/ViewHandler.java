package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Institution;
import persistence.ModelManager;

public class ViewHandler {

    private Stage primaryStage;
    private Institution institution;
    private ModelManager modelManager;

    private model.Room currentRoom;

    public ViewHandler(Stage stage) {
        this.primaryStage = stage;
        this.modelManager = new ModelManager("institutionData.bin");
        this.institution = modelManager.load();
    }

    public Institution getInstitution() {
        return institution;
    }

    public void saveInstitution() {
        modelManager.save(institution);
    }

    public model.Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(model.Room room) {
        this.currentRoom = room;
    }

    // HOVEDSKÆRM
    public void openMainView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SimpleGUI.fxml"));
            Scene scene = new Scene(loader.load());

            SimpleGUIController controller = loader.getController();
            controller.init(this);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //  PERSONALE
    public void openStaffView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/StaffView.fxml"));
            Scene scene = new Scene(loader.load());

            StaffViewController controller = loader.getController();
            controller.init(this);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // ===== DE ANDRE =====
    public void openAgendaView() { openView("/view/AgendaView.fxml"); }
    public void openInfoView() { openView("/view/InfoView.fxml"); }
    public void openCriticalView() { openView("/view/CriticalView.fxml"); }
    public void openWeatherView() { openView("/view/WeatherView.fxml"); }
    public void openChildrenView() {openView("/view/ChildrenView.fxml");}


    private void openView(String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
            Scene scene = new Scene(loader.load());

            Object controller = loader.getController();
            controller.getClass().getMethod("init", ViewHandler.class).invoke(controller, this);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
