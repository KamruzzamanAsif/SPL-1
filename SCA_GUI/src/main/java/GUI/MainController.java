package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onSCAClick() {
        welcomeText.setText("Welcome to SCA");
    }
}