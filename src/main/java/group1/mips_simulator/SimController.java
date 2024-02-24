package group1.mips_simulator;

import group1.mips_simulator.FrontEnd.Redraw;
import group1.mips_simulator.components.Computer;
import group1.mips_simulator.components.Value;
import group1.mips_simulator.components.cpuParts.Register;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SimController {

    public Computer computer;
    Redraw redraw = new Redraw();


    public SimController(Computer c) {
        this.computer = c;
    }

    //region Binary Displays

    //region Left Column (General Purpose Registers)

    public VBox VBoxLeft_GPRs;
    public TextField GPR0;
    public TextField GPR1;
    public TextField GPR2;
    public TextField GPR3;
    public Button GPR0_button;
    public Button GPR1_button;
    public Button GPR2_button;
    public Button GPR3_button;


    //endregion

    //region Middle Column (Index Registers)
    public VBox VBoxCenter_IXRs;
    public TextField IXR1;
    public TextField IXR2;

    public TextField IXR3;
    public Button IXR1_button;
    public Button IXR2_button;
    public Button IXR3_button;


    // endregion

    //region Right Column (Other Registers)
    public VBox VBoxRight_OtherRegs;
    public TextField PC;
    public TextField MAR;
    public TextField MBR;
    public TextField RI;
    public TextField MFR;
    public TextField Privileged;
    public TextField CC;

    public Button PC_button;
    public Button MAR_button;
    public Button MBR_button;
    public Button RI_button;

    //endregion

    //endregion

    //region Command Buttons

    public void loadOnClick() {
    }

    public void loadPlusOnClick(ActionEvent actionEvent) {
    }

    public void storeOnClick(ActionEvent actionEvent) {
    }

    public void storePlusOnClick(ActionEvent actionEvent) {
    }

    public void runOnClick(ActionEvent actionEvent) {
    }

    public void stepOnClick(ActionEvent actionEvent) {
    }

    public void haltOnClick(ActionEvent actionEvent) {
    }

    //endregion

    //region Input Numbers
    public HBox hbox_UserInput;
    @FXML
    public TextField BinInput;
    public TextField OctalInput;

    //endregion

    //region Load File
    public TextField FileToLoad;

    public void iplOnClick(ActionEvent actionEvent) {
    }

    //endregion
    @FXML
    public void initialize() {
        System.out.println("Hello from SimController.initialize");
        BinInput.textProperty().addListener((obs, oldText, newText) -> {
            if (!Utility.isValidBinary(newText)) {
                BinInput.setStyle("-fx-text-fill: red"); // Color of text
                return;
            }
            // Else it's a valid number
            BinInput.setStyle("-fx-text-fill: black");// Color of text
            OctalInput.setText(Utility.binaryStrToOctalStr(newText));
        });
        OctalInput.textProperty().addListener((obs, oldText, newText) -> {
            if (!Utility.isValidOctal(newText)) {
                OctalInput.setStyle("-fx-text-fill: red"); // Color of text
                return;
            }
            // Else it's a valid number
            OctalInput.setStyle("-fx-text-fill: black");// Color of text
            BinInput.setText(Utility.octalStringToBinaryString(newText));
        });

        setupButton(GPR0_button, computer.cpu.regfile.getGPR(0));
        setupButton(GPR1_button, computer.cpu.regfile.getGPR(1));
        setupButton(GPR2_button, computer.cpu.regfile.getGPR(2));
        setupButton(GPR3_button, computer.cpu.regfile.getGPR(3));

        setupButton(IXR1_button, computer.cpu.regfile.getIXR(1));
        setupButton(IXR2_button, computer.cpu.regfile.getIXR(2));
        setupButton(IXR3_button, computer.cpu.regfile.getIXR(3));

        setupButton(PC_button, computer.cpu.regfile.getPC());
        setupButton(MAR_button, computer.cpu.regfile.getMAR());
        setupButton(MBR_button, computer.cpu.regfile.getMBR());
        setupButton(RI_button, computer.cpu.regfile.getIR());

        // Draw the initial state
        redraw();
    }


    public void redraw() {
        this.redraw.updateFrontEnd(this, computer);
    }

    public void setupButton(Button button, Register r) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Clicked button " + button.getId());
                String userInputBinStr = BinInput.getText().replace(" ", "");
                if (!Utility.isValidBinary(userInputBinStr)) {
                    return;
                }
                // Else it is a valid binary
                r.write(new Value(Utility.binaryToShort(userInputBinStr)));
                redraw();
            }
        });
    }
}
