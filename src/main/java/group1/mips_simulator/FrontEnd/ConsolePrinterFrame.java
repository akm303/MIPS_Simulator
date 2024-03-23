package group1.mips_simulator.FrontEnd;

import javax.swing.*;
import java.awt.*;

public class ConsolePrinterFrame {

    public static int WIDTH = 500;
    public static int HEIGHT = 300;

    JFrame myFrame;
    public JLabel printerLabel;

    public ConsolePrinterFrame(int x, int y) {
        myFrame = makeNewPrinterFrame();
        this.myFrame.setLocation(x, y);
    }

    JFrame makeNewPrinterFrame() {
        JFrame printerFrame = new JFrame("Console Keyboard");
        printerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        printerFrame.setLayout(new FlowLayout());
        printerFrame.setSize(WIDTH, HEIGHT);


        printerLabel = new JLabel();
        Dimension size = new Dimension(WIDTH, HEIGHT);
        printerLabel.setSize(size);
        printerLabel.setPreferredSize(size);
        printerLabel.setMaximumSize(size);
        printerLabel.setMinimumSize(size);

        printerFrame.add(printerLabel);
        printerLabel.setText("<html></html>");

        printerFrame.setVisible(true);
        return printerFrame;
    }

    public void addCharacter(char c) {
        if(c == '\n') {
            printerLabel.setText(printerLabel.getText() + "<br>");
            return;
        }
        printerLabel.setText(printerLabel.getText() + c);
    }
}
