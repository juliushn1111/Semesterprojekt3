package view;

import javafx.fxml.FXML;

public class InfoViewController {

    private ViewHandler viewHandler;

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
