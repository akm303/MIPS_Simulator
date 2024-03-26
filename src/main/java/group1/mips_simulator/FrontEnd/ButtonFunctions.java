package group1.mips_simulator.FrontEnd;

import group1.mips_simulator.Utility;
import group1.mips_simulator.components.Computer;
import group1.mips_simulator.components.ROM;
import group1.mips_simulator.components.cpuParts.Register;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class ButtonFunctions {

    void onRegButtonClick(Register reg) {
        JTextField binInput = SwingConsole.textFields.get("BinInput");
        String userInputBinStr = binInput.getText().replace(" ", "");
        if (!Utility.isValidBinary(userInputBinStr)) {
            return;
        }
        //else it's valid binary
        reg.write(Utility.binaryToShort(userInputBinStr));
        SwingConsole.redraw();
    }

    void setupButton(JButton button, Register reg) {
        button.addActionListener(e -> onRegButtonClick(reg));
    }

    public void setupAllRegisterButtons(HashMap<String, JButton> buttonFields, Computer computer) {
        setupButton(buttonFields.get("GPR0"), computer.cpu.regfile.getGPR(0));
        setupButton(buttonFields.get("GPR1"), computer.cpu.regfile.getGPR(1));
        setupButton(buttonFields.get("GPR2"), computer.cpu.regfile.getGPR(2));
        setupButton(buttonFields.get("GPR3"), computer.cpu.regfile.getGPR(3));

        setupButton(buttonFields.get("IXR1"), computer.cpu.regfile.getIXR(1));
        setupButton(buttonFields.get("IXR2"), computer.cpu.regfile.getIXR(2));
        setupButton(buttonFields.get("IXR3"), computer.cpu.regfile.getIXR(3));


        setupButton(buttonFields.get("PC"), computer.cpu.regfile.getPC());
        setupButton(buttonFields.get("MAR"), computer.cpu.regfile.getMAR());
        setupButton(buttonFields.get("MBR"), computer.cpu.regfile.getMBR());
        setupButton(buttonFields.get("IR"), computer.cpu.regfile.getIR());
    }

    //////////////////////////////////////////////////////////////////////

    /**
     * Use the value in the Memory Address Register, go to that location in Memory
     * and read the value from memory into the Memory Buffer Register
     */
    void loadOnClick(Computer computer) {
        Register mar = computer.cpu.regfile.getMAR();
        short valueInMemory = computer.memory.read(mar.read());
        System.out.println("MemLoc -> Value : " + mar.read() + "   " + valueInMemory);
        computer.cpu.regfile.getMBR().write(valueInMemory);
        SwingConsole.redraw(computer);
    }

    /**
     * Similar to the loadOnClick function (read value from memory pointed
     * to by MAR into MBR) but this will also increase the MAR AFTER
     * the read.
     */
    void loadPlusOnClick(Computer computer) {
        loadOnClick(computer);
        Register memAddReg = computer.cpu.regfile.getMAR();
        memAddReg.write((short) (memAddReg.read() + 1));
        SwingConsole.redraw(computer);
    }

    /**
     * Take the value in the Memory Buffer Register, and put it into memory
     * at the location specified in the Memory Address Register
     */
    void storeOnClick(Computer computer) {
        Register memBuffReg = computer.cpu.regfile.getMBR();
        Register memAddReg = computer.cpu.regfile.getMAR();
        computer.memory.write(memAddReg.read(), memBuffReg.read());
        SwingConsole.redraw(computer);
    }


    /**
     * The same as the storeOnClick function, except this will increment the location
     * of the Memory Address Register BEFORE writing to memory
     */
    void storePlusOnClick(Computer computer) {
        Register memAddReg = computer.cpu.regfile.getMAR();
        memAddReg.write((short) (memAddReg.read() + 1));
        storeOnClick(computer);
        SwingConsole.redraw(computer);
    }

    /**
     * Spin up a parallel thread and start running the program on that background thread
     * This allows the main thread to listen for HALT events
     */
    void runOnClick(Computer computer) {
        // Halt any previous task
        SwingConsole.runBgTask.halt();

        // Create, save and start new BG task
        RunBackgroundTask bgTask = new RunBackgroundTask(computer);
        SwingConsole.runBgTask = bgTask;
        bgTask.execute();
    }

    /**
     * Only run a single instruction, from the current Program Counter.
     * Redraw the frame afterward.
     */
    void stepOnClick(Computer computer) {
        computer.runCurrentPC();
        SwingConsole.redraw(computer);
    }

    /**
     * Halt any existing program (if one is running).
     */
    void haltOnClick(Computer computer) {
        System.out.println("HALTING!!!!!!!!!!!!!!!!!!!!!!!");
        SwingConsole.runBgTask.halt();
        SwingConsole.redraw(computer);
        System.out.println("Finished HALTING!!!!!!!!!!!!!!!!!!!!!!!");
    }

    public void setupAllFunctionButtons(HashMap<String, JButton> buttonFields, Computer computer) {
        buttonFields.get("Load").addActionListener(e -> loadOnClick(computer));
        buttonFields.get("Load+").addActionListener(e -> loadPlusOnClick(computer));
        buttonFields.get("Store").addActionListener(e -> storeOnClick(computer));
        buttonFields.get("Store+").addActionListener(e -> storePlusOnClick(computer));

        buttonFields.get("Run").addActionListener(e -> runOnClick(computer));
        buttonFields.get("Step").addActionListener(e -> stepOnClick(computer));
        buttonFields.get("Halt").addActionListener(e -> haltOnClick(computer));
    }

    //////////////////////////////////////////////////////////////////////

    void iplOnClick(Computer computer) {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "Binary Files", "txt", "bi");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal != JFileChooser.APPROVE_OPTION) {
            return;
        }
        File selectedFile = chooser.getSelectedFile();
        System.out.println(selectedFile.getAbsolutePath());
        SwingConsole.textFields.get("IPL").setText(selectedFile.getAbsolutePath());

        // Hand the file to the ROM loader
        try {
            ROM rom = new ROM();
            rom.readFromFile(selectedFile);
            computer.loadROM(rom);

            System.out.println("Finished loading rom");
            SwingConsole.textFields.get("IPL").setBackground(Color.WHITE);
        } catch (IOException e) {
            System.out.println("Encountered an error when reading file: " + e);
            SwingConsole.textFields.get("IPL").setBackground(Color.PINK);
        }
    }

    public void SetUpIplButtons(HashMap<String, JButton> buttonFields, Computer computer) {
        buttonFields.get("IPL").addActionListener(e -> iplOnClick(computer));
    }
}
