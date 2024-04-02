package group1.mips_simulator.FrontEnd;

import javax.swing.*;
import java.awt.*;

public class ConsolePrinterFrame {

    public static int WIDTH = 500;
    public static int HEIGHT = 300;

    JFrame myFrame;
    public JLabel printerLabel;
    protected StringBuilder currentText = new StringBuilder();

    public ConsolePrinterFrame(int x, int y) {
        myFrame = makeNewPrinterFrame();
        this.myFrame.setLocation(x, y);
    }

    JFrame makeNewPrinterFrame() {
        JFrame printerFrame = new JFrame("Console Printer");
        printerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        printerFrame.setSize(WIDTH, HEIGHT);


        printerLabel = new JLabel();
        Dimension size = new Dimension(WIDTH, HEIGHT);
        printerLabel.setSize(size);
        printerLabel.setPreferredSize(size);
        printerLabel.setMaximumSize(size);
        printerLabel.setMinimumSize(size);

        printerFrame.add(printerLabel);
        printerLabel.setText("<html>...</html>");
        printerLabel.setVisible(true);

        printerFrame.setVisible(true);
        return printerFrame;
    }

    public void addCharacter(char c) {
        System.out.println("Printer frame adding char: " + c);
        if(c == '\n') {
            currentText.append("<br>");
        }else {
            currentText.append(c);
        }
        redraw();
    }

    private void redraw() {
        System.out.println("Printer frame redrawing with text: " + currentText.toString());
        printerLabel.setText("<html>" + currentText.toString() + "</html>");
    }
}
