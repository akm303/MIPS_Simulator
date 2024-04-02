package group1.mips_simulator.FrontEnd;

import group1.mips_simulator.components.Computer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class SwingConsole {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 550;

    public static Computer computer = new Computer();

    public static RunBackgroundTask runBgTask = new RunBackgroundTask(computer);

    public static HashMap<String, JTextField> textFields = new HashMap<>();
    public static HashMap<String, JButton> buttonFields = new HashMap<>();

    static ConsoleKeyboardFrame keyboardGui;
    static ConsolePrinterFrame printerGui;
    static CacheFrame cacheGui;

    public static void main(String[] args) {
        JFrame f = new JFrame("Mips Computer Front Panel");
        f.setLayout(new FlowLayout());
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(WIDTH, HEIGHT);
        SwingFactory factory = new SwingFactory();
        textFields = factory.buildAllTextFields();
        buttonFields = factory.buildAllButtons();

        f.add(factory.buildRegisterDisplay(textFields, buttonFields));
        f.add(factory.buildUserInput(textFields, buttonFields));
        f.add(factory.buildIplSection(textFields, buttonFields));

        ButtonFunctions bfs = new ButtonFunctions();
        bfs.setupAllRegisterButtons(buttonFields, computer);
        bfs.setupAllFunctionButtons(buttonFields, computer);
        bfs.SetUpIplButtons(buttonFields, computer);


        f.setVisible(true);

        keyboardGui = new ConsoleKeyboardFrame(f.getX() + WIDTH, f.getY());
        printerGui = new ConsolePrinterFrame(f.getX() + WIDTH, f.getY() + 100);
        cacheGui = new CacheFrame(f.getX() + WIDTH, f.getY() + 100 + ConsolePrinterFrame.HEIGHT);

        redraw();
    }

    public static void redraw() {
        // Update all the GPRs
        textFields.get("GPR0").setText(computer.cpu.regfile.getGPR(0).toString_Binary());
        textFields.get("GPR1").setText(computer.cpu.regfile.getGPR(1).toString_Binary());
        textFields.get("GPR2").setText(computer.cpu.regfile.getGPR(2).toString_Binary());
        textFields.get("GPR3").setText(computer.cpu.regfile.getGPR(3).toString_Binary());

        // Update all IXRs
        textFields.get("IXR1").setText(computer.cpu.regfile.getIXR(1).toString_Binary());
        textFields.get("IXR2").setText(computer.cpu.regfile.getIXR(2).toString_Binary());
        textFields.get("IXR3").setText(computer.cpu.regfile.getIXR(3).toString_Binary());

        // Update other registers
        textFields.get("PC").setText(computer.cpu.regfile.getPC().toString_Binary());
        textFields.get("MAR").setText(computer.cpu.regfile.getMAR().toString_Binary());
        textFields.get("MBR").setText(computer.cpu.regfile.getMBR().toString_Binary());
        textFields.get("IR").setText(computer.cpu.regfile.getIR().toString_Binary());
        textFields.get("MFR").setText(computer.cpu.regfile.getMFR().toString_Binary());
        textFields.get("CC").setText(computer.cpu.regfile.getCC().toString_Binary());

        // Update the cache
        cacheGui.redrawCache(computer.cache);
    }

}

