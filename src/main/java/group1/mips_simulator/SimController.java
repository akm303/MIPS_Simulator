package group1.mips_simulator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class SimController {
    public TextField GPR1;
    public TextField GPR2;
    public TextField GPR3;
    @FXML
    public TextField GPR0;
    public TextField IXR3;
    public TextField IXR2;
    public TextField IXR1;
    public VBox VBoxLeft_GPRs;
    public VBox VBoxCenter_IXRs;
    public VBox VBoxRight_OtherRegs;
    public TextField PC;
    public TextField MAR;
    public TextField MBR;
    public TextField RI;
    public TextField MFR;
    public TextField Privileged;
    public TextField CC;

    @FXML
    private Label welcomeText;

    @FXML
    private Label pcLabel;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}