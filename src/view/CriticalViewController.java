package view;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.CriticalInfo;
import model.Institution;

public class CriticalViewController {

    @FXML
    private ListView<String> criticalListView;

    @FXML
    private TextField criticalInputField;

    private ViewHandler viewHandler;
    private Institution institution;

    private int selectedIndex = -1;

    public void init(ViewHandler handler) {
        this.viewHandler = handler;
        this.institution = handler.getInstitution();

        criticalListView.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            selectedIndex = newVal.intValue();
            loadSelectedIntoField();
        });

        updateCriticalView();
    }

    private void updateCriticalView() {
        criticalListView.getItems().clear();

        for (CriticalInfo ci : institution.getCriticalInfoList()) {
            criticalListView.getItems().add(ci.getKritik());
        }
    }

    @FXML
    private void handleAddCritical() {
        String text = criticalInputField.getText().trim();
        if (text.isEmpty()) return;

        institution.addCriticalInfo(new CriticalInfo(text));

        criticalInputField.clear();
        updateCriticalView();
        viewHandler.saveInstitution();
    }

    @FXML
    private void handleDeleteCritical() {
        if (selectedIndex < 0) return;

        institution.removeCriticalInfo(selectedIndex);

        criticalInputField.clear();
        selectedIndex = -1;

        updateCriticalView();
        viewHandler.saveInstitution();
    }

    @FXML
    private void handleEditCritical() {
        if (selectedIndex < 0) return;

        String newText = criticalInputField.getText().trim();
        if (newText.isEmpty()) return;

        institution.getCriticalInfoList().get(selectedIndex).setKritik(newText);

        updateCriticalView();
        viewHandler.saveInstitution();
    }

    private void loadSelectedIntoField() {
        if (selectedIndex < 0) return;

        String text = institution.getCriticalInfoList().get(selectedIndex).getKritik();
        criticalInputField.setText(text);
    }

    @FXML
    private void handleBackButton() {
        viewHandler.openMainView();
    }
}
