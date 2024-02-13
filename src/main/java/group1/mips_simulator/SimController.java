package group1.mips_simulator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SimController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}