package view;

import javafx.fxml.FXML;

public class WeatherViewController {

    private ViewHandler viewHandler;

    // Bliver kaldt fra ViewHandler, når FXML'en er loadet
    public void init(ViewHandler viewHandler) {
        this.viewHandler = viewHandler;
    }

    @FXML
    private void handleBackButton() {
        if (viewHandler != null) {
            viewHandler.openMainView();
        }
    }
}
