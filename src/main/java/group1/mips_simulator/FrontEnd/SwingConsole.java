package group1.mips_simulator.FrontEnd;

import javax.swing.*;
import java.awt.*;

public class SwingConsole {

    public static final int WIDTH = 1100;
    public static final int HEIGHT = 750;

    public static void main(String[] args) {
        JFrame f = new JFrame("Mips Computer Front Panel");
        f.setLayout(new GridLayout(3, 1));
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(WIDTH, HEIGHT);
        SwingFactory factory = new SwingFactory();

        f.add(factory.buildRegisterDisplay());
        JTextField blab = new JTextField("Some example text field");
        blab.setVisible(true);
        f.add(blab);



        f.setVisible(true);
    }

    public static void main2(String[] args) {
        JFrame frame = new JFrame("Read-Only TextField Example");
        frame.setSize(300, 200);

        // Create a read-only TextField
        JTextField readOnlyTextField = new JTextField("This is a read-only TextField");
        readOnlyTextField.setEditable(false); // Set editable property to false

        // Add the read-only TextField to the frame
        frame.add(readOnlyTextField);

        // Set the frame visible after adding components
        frame.setVisible(true);
    }
}
