package group1.mips_simulator.FrontEnd;

import group1.mips_simulator.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class ConsoleKeyboardFrame {
    public static int WIDTH = 500;
    public static int HEIGHT = 100;

    public static final char TERMINATOR = '\0';

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
            SwingConsole.buttonFields.get("Run").doClick();
        });
    }

    void userNotDone() {
        this.userDone = false;
        this.userTextInput.setBackground(Color.PINK);
    }

    void userDone() {
        this.userDone = true;

        // Remove any added null terminators
        String currentText = this.userTextInput.getText();
        currentText = currentText.replace(("" + '\0'), "");

        // Add the terminator to the end
        currentText = currentText + TERMINATOR;

        this.userTextInput.setText(currentText);
        this.userTextInput.setBackground(Color.WHITE);
    }

    public Character getNextChar() {
        if (!userDone || this.userTextInput.getText().isEmpty()) {
            return null;
        }
        String currentText = this.userTextInput.getText();
        Character result = currentText.charAt(0); // Grab the 0th character
        this.userTextInput.setText(currentText.substring(1)); // strip it from the remaining string
        return result;
    }
}
