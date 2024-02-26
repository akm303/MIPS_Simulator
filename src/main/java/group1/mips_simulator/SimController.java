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
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class SimController {

    public Computer computer;
    private final Stage mainStage;
    Redraw redraw = new Redraw();


    public SimController(Computer c, Stage stage) {
        this.computer = c;
        mainStage = stage;
        // do NOT call restartComputer here.
    }

    protected void restartComputer() {
        this.computer = new Computer();
        this.initialize();
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
    public TextField IR;
    public TextField MFR;
    public TextField Privileged;
    public TextField CC;

    public Button PC_button;
    public Button MAR_button;
    public Button MBR_button;
    public Button IR_button;

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
    @FXML
    public TextField FileToLoad;
    public Button IPL_button;

    //endregion

    // Hook up all the front end elements to their back end models
    // Hook up user interface buttons with the back end controller functions
    @FXML
    public void initialize() {
        // do NOT call restartComputer here.

        System.out.println("Hello from SimController.initialize");
        BinInput.textProperty().addListener((obs, oldText, newText) -> {
            if (!Utility.isValidBinary(newText)) {
                BinInput.setStyle("-fx-text-fill: red"); // Color of text
                OctalInput.setText("");
                return;
            }
            // Else it's a valid number
            BinInput.setStyle("-fx-text-fill: black");// Color of text
            OctalInput.setText(Utility.binaryStrToOctalStr(newText));
        });
        OctalInput.textProperty().addListener((obs, oldText, newText) -> {
            if (!Utility.isValidOctal(newText)) {
                OctalInput.setStyle("-fx-text-fill: red"); // Color of text
                BinInput.setText("");
                return;
            }
            // Else it's a valid number
            OctalInput.setStyle("-fx-text-fill: black");// Color of text
            BinInput.setText(Utility.octalStringToBinaryString(newText));
        });

        setupRegisterButton(GPR0_button, computer.cpu.regfile.getGPR(0));
        setupRegisterButton(GPR1_button, computer.cpu.regfile.getGPR(1));
        setupRegisterButton(GPR2_button, computer.cpu.regfile.getGPR(2));
        setupRegisterButton(GPR3_button, computer.cpu.regfile.getGPR(3));

        setupRegisterButton(IXR1_button, computer.cpu.regfile.getIXR(1));
        setupRegisterButton(IXR2_button, computer.cpu.regfile.getIXR(2));
        setupRegisterButton(IXR3_button, computer.cpu.regfile.getIXR(3));

        setupRegisterButton(PC_button, computer.cpu.regfile.getPC());
        setupRegisterButton(MAR_button, computer.cpu.regfile.getMAR());
        setupRegisterButton(MBR_button, computer.cpu.regfile.getMBR());
        setupRegisterButton(IR_button, computer.cpu.regfile.getIR());

        setupIPL_Button(mainStage, new FileReader());
        // Draw the initial state
        redraw();
    }


    public void redraw() {
        this.redraw.updateFrontEnd(this, computer);
    }

    public void setupRegisterButton(Button button, Register r) {
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

    public void setupIPL_Button(Stage mainStage, FileReader fr) {
        // Restart/ wipe the computer
        // Ask the user for a .bi file
        // Then had that to the ROM loader
        // If there is any issue, then paint the file name red.

        IPL_button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("IPL button clicked");
                // restart the computer
                restartComputer();

                // Prompt user for a .bi file
                File selectedFile = fr.getFile(mainStage);
                FileToLoad.setText(selectedFile.getAbsolutePath());

                // Hand the file to the ROM loader
                try {
                    computer.readOnlyMemory.readFromFile(selectedFile);
                    FileToLoad.setStyle("-fx-text-fill: black"); // Color of text
                } catch (IOException e) {
                    System.out.println("Encountered an error when reading file: " + e);
                    FileToLoad.setStyle("-fx-text-fill: red"); // Color of text
                }
            }
        });
    }

}
