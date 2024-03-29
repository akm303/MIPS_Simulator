package group1.mips_simulator.FrontEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConsoleKeyboardFrame {
    public static int WIDTH = 500;
    public static int HEIGHT = 100;

    boolean userDone = false;

    JFrame myFrame;
    JTextField userTextInput;
    JButton userDoneButton;

    public ConsoleKeyboardFrame(int x, int y) {
        myFrame = makeNewKeyboardFrame();
        this.setupInputTextField();
        this.setupDoneButton();

        this.myFrame.setLocation(x, y);
    }

    JFrame makeNewKeyboardFrame() {
        JFrame keyboardFrame = new JFrame("Console Keyboard");
        keyboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        keyboardFrame.setLayout(new GridLayout(2, 1));
        keyboardFrame.setSize(WIDTH, HEIGHT);

        userTextInput = new JTextField();
        Dimension size = new Dimension(400, 20);
        userTextInput.setSize(size);
        userTextInput.setPreferredSize(size);
        userTextInput.setMaximumSize(size);
        userTextInput.setMinimumSize(size);
        keyboardFrame.add(userTextInput);

        userDoneButton = new JButton("Done");
        keyboardFrame.add(userDoneButton);

        keyboardFrame.setVisible(true);
        return keyboardFrame;
    }

    void setupInputTextField() {
        this.userTextInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                userNotDone();
            }
        });
    }

    void setupDoneButton() {
        this.userDoneButton.addActionListener(e -> {
            userDone();
        });
    }

    void userNotDone() {
        this.userDone = false;
        this.userTextInput.setBackground(Color.PINK);
    }

    void userDone() {
        this.userDone = true;
        this.userTextInput.setBackground(Color.WHITE);
    }

}
