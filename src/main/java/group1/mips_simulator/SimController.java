package group1.mips_simulator;

import group1.mips_simulator.FrontEnd.Redraw;
import group1.mips_simulator.components.Computer;
import group1.mips_simulator.components.Value;
import group1.mips_simulator.components.cpuParts.Register;
import javafx.application.Platform;
import javafx.event.ActionEvent;
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
    Thread programRunnerThread = null;
    private final Stage mainStage;
    private boolean userHalt = false;
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

    @FXML
    public Button loadButton;
    @FXML
    public Button loadPlusButton;
    @FXML
    public Button storeButton;
    @FXML
    public Button storePlusButton;

    @FXML
    public Button run_Button;
    @FXML
    public Button step_Button;
    @FXML
    public Button halt_Button;

    /**
     * Use the value in the Memory Address Register, go to that location in Memory
     * and read the value from memory into the Memory Buffer Register
     */
    public void loadOnClick(ActionEvent actionEvent) {
        Register memAddReg = this.computer.cpu.regfile.getMAR();
        short valueInMemory = this.computer.mem.read(memAddReg.read());
        this.computer.cpu.regfile.getMBR().write(valueInMemory);
        redraw();
    }

    /**
     * Similar to the loadOnClick function (read value from memory pointed
     * to by MAR into MBR) but this will also increase the MAR AFTER
     * the read.
     */
    public void loadPlusOnClick(ActionEvent actionEvent) {
        loadOnClick(actionEvent);
        Register memAddReg = this.computer.cpu.regfile.getMAR();
        memAddReg.write((short) (memAddReg.read() + 1));
        redraw();
    }

    /**
     * Take the value in the Memory Buffer Register, and put it into memory
     * at the location specified in the Memory Address Register
     */
    public void storeOnClick(ActionEvent actionEvent) {
        Register memBuffReg = this.computer.cpu.regfile.getMBR();
        Register memAddReg = this.computer.cpu.regfile.getMAR();
        this.computer.mem.write(memAddReg.get(), memBuffReg.get());
        redraw();
    }

    /**
     * The same as the storeOnClick function, except this will increment the location
     * of the Memory Address Register BEFORE writing to memory
     */
    public void storePlusOnClick(ActionEvent actionEvent) {
        Register memAddReg = this.computer.cpu.regfile.getMAR();
        memAddReg.write((short) (memAddReg.read() + 1));
        storeOnClick(actionEvent);
        redraw();
    }

    /**
     * Spin up a parallel thread and start running the program on that background thread
     * This allows the main thread to listen for HALT events
     */
    public void runOnClick(ActionEvent actionEvent) {
        // Halt any previous task
        this.haltOnClick(actionEvent);
        this.userHalt = false;

        // Run the task in a background thread so the main thread
        // may listen for interrupt activities (or for other button clicks
        // the user may make)
        this.programRunnerThread = new Thread(this::runOnClickTask);
        // Bind the thread to the main one (to stop both in case of user closing the window)
        programRunnerThread.setDaemon(true);
        // Start the program
        programRunnerThread.start();
    }

    /**
     * This function is the "task" to be run on the parallel thread.
     * NOTE: This functions features a (potentially) infinite while(true)
     * loop. Only a haltOnClick() event, or the program's natural termination
     * will stop the while(true) loop.
     */
    private void runOnClickTask() {
        boolean computerMayContinue = true; // Track if the program is requesting a halt

        // Run the instruction at the current Program Counter forever until the user
        // or the computer itself requests a halt.
        while (!userHalt && computerMayContinue) {
            System.out.println("Running Instruction");
            computerMayContinue = this.computer.runCurrentPC();
        }
        Platform.runLater(this::redraw);
    }

    /**
     * Only run a single instruction, from the current Program Counter.
     * Redraw the frame afterward.
     */
    public void stepOnClick(ActionEvent actionEvent) {
        this.computer.runCurrentPC();
        this.redraw();
    }

    /**
     * Halt any existing program (if one is running).
     */
    public void haltOnClick(ActionEvent actionEvent) {
        System.out.println("HALTING!!!!!!!!!!!!!!!!!!!!!!!");
        userHalt = true;
        if (programRunnerThread != null) {
            programRunnerThread.interrupt();
        }

        System.out.println("Halt Redrawing");
        // redraw the frame after forcing the program to stop early to render the
        // changes in the Model to the View.
        redraw();
        System.out.println("Finished HALTING!!!!!!!!!!!!!!!!!!!!!!!");
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

        loadButton.setOnAction(this::loadOnClick);
        loadPlusButton.setOnAction(this::loadPlusOnClick);
        storeButton.setOnAction(this::storeOnClick);
        storePlusButton.setOnAction(this::storePlusOnClick);

        run_Button.setOnAction(this::runOnClick);
        step_Button.setOnAction(this::stepOnClick);
        halt_Button.setOnAction(this::haltOnClick);


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
        button.setOnAction(actionEvent -> {
            System.out.println("Clicked button " + button.getId());
            String userInputBinStr = BinInput.getText().replace(" ", "");
            if (!Utility.isValidBinary(userInputBinStr)) {
                return;
            }
            // Else it is a valid binary
            r.write(Utility.binaryToShort(userInputBinStr));
            redraw();
        });
    }

    public void setupIPL_Button(Stage mainStage, FileReader fr) {
        // Restart/ wipe the computer
        // Ask the user for a .bi file
        // Then had that to the ROM loader
        // If there is any issue, then paint the file name red.

        IPL_button.setOnAction(actionEvent -> {
            System.out.println("IPL button clicked");
            // restart the computer
            restartComputer();

            // Prompt user for a .bi file
            File selectedFile = fr.getFile(mainStage);
            FileToLoad.setText(selectedFile.getAbsolutePath());

            // Hand the file to the ROM loader
            try {
                computer.rom.readFromFile(selectedFile);
                FileToLoad.setStyle("-fx-text-fill: black"); // Color of text
            } catch (IOException e) {
                System.out.println("Encountered an error when reading file: " + e);
                FileToLoad.setStyle("-fx-text-fill: red"); // Color of text
            }
        });
    }

}
