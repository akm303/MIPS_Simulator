package group1.mips_simulator.FrontEnd;

import group1.mips_simulator.Utility;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class SwingFactory {

    public HashMap<String, JTextField> buildAllTextFields() {
        HashMap<String, JTextField> result = new HashMap<>();
        result.put("GPR0", new JTextField());
        result.put("GPR1", new JTextField());
        result.put("GPR2", new JTextField());
        result.put("GPR3", new JTextField());

        result.put("IXR1", new JTextField());
        result.put("IXR2", new JTextField());
        result.put("IXR3", new JTextField());

        result.put("PC", new JTextField());
        result.put("MAR", new JTextField());
        result.put("MBR", new JTextField());
        result.put("IR", new JTextField());
        result.put("MFR", new JTextField());
        result.put("CC", new JTextField());
        result.put("Privileged", new JTextField());
        result.put("CC (OUED)", new JTextField());

        result.put("BinInput", new JTextField());
        result.put("OctInput", new JTextField());

        result.put("IPL", new JTextField());

        return result;
    }

    public HashMap<String, JButton> buildAllButtons() {
        HashMap<String, JButton> result = new HashMap<>();
        result.put("GPR0", new JButton());
        result.put("GPR1", new JButton());
        result.put("GPR2", new JButton());
        result.put("GPR3", new JButton());

        result.put("IXR1", new JButton());
        result.put("IXR2", new JButton());
        result.put("IXR3", new JButton());

        result.put("PC", new JButton());
        result.put("MAR", new JButton());
        result.put("MBR", new JButton());
        result.put("IR", new JButton());

        result.put("Load", new JButton("Load"));
        result.put("Load+", new JButton("Load+"));
        result.put("Store", new JButton("Store"));
        result.put("Store+", new JButton("Store+"));

        result.put("Run", new JButton("Run"));
        result.put("Step", new JButton("Step"));
        result.put("Halt", new JButton("Halt"));

        result.put("IPL", new JButton("IPL"));

        return result;
    }

    JPanel buildRegisterDisplay_NoButton(String regId, HashMap<String, JTextField> textFields) {
        JPanel regDisplayGroup = new JPanel();
        regDisplayGroup.setLayout(new FlowLayout(FlowLayout.TRAILING));
        //regDisplayGroup.setLayout(new GridLayout(1, 3));

        // Label
        JLabel regLabel = new JLabel(regId + ": ");
        regLabel.setSize(4, 1);
        regDisplayGroup.add(regLabel);

        // Text field
        JTextField regContents = textFields.get(regId);
        regContents.setEditable(false); // read only
        regContents.setText("0000");
        //regContents.setMinimumSize(new Dimension(50, 20));
        regDisplayGroup.add(regContents);

        regDisplayGroup.setVisible(true);
        return regDisplayGroup;
    }

    JPanel buildRegisterDisplay(String regId, HashMap<String, JTextField> textFields, HashMap<String, JButton> buttons) {

        JPanel regDisplayGroup = new JPanel();
        regDisplayGroup.setLayout(new FlowLayout(FlowLayout.TRAILING));
        //regDisplayGroup.setLayout(new GridLayout(1, 3));

        // Label
        JLabel regLabel = new JLabel(regId + ": ");
        regLabel.setSize(4, 1);
        regDisplayGroup.add(regLabel);

        // Text field
        JTextField regContents = textFields.get(regId);
        regContents.setEditable(false); // read only
        regContents.setText("0000_0000_0000_0000");
        //regContents.setMinimumSize(new Dimension(50, 20));
        regDisplayGroup.add(regContents);

        // Button
        JButton button = buttons.get(regId);
        Dimension buttonSize = new Dimension(20, 20);
        button.setSize(buttonSize);
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);
        button.setMinimumSize(buttonSize);
        regDisplayGroup.add(button);

        regDisplayGroup.setVisible(true);
        return regDisplayGroup;
    }

    JPanel buildLeftColumn(HashMap<String, JTextField> textFields, HashMap<String, JButton> buttons) {
        JPanel leftColumn = new JPanel(new GridLayout(7, 1));
        leftColumn.add(buildRegisterDisplay("GPR0", textFields, buttons));
        leftColumn.add(buildRegisterDisplay("GPR1", textFields, buttons));
        leftColumn.add(buildRegisterDisplay("GPR2", textFields, buttons));
        leftColumn.add(buildRegisterDisplay("GPR3", textFields, buttons));

        leftColumn.setVisible(true);
        return leftColumn;
    }

    JPanel buildCenterColumn(HashMap<String, JTextField> textFields, HashMap<String, JButton> buttons) {
        JPanel column = new JPanel(new GridLayout(7, 1));
        column.add(buildRegisterDisplay("IXR1", textFields, buttons));
        column.add(buildRegisterDisplay("IXR2", textFields, buttons));
        column.add(buildRegisterDisplay("IXR3", textFields, buttons));

        column.setVisible(true);
        return column;
    }

    JPanel buildRightColumn(HashMap<String, JTextField> textFields, HashMap<String, JButton> buttons) {
        JPanel column = new JPanel(new GridLayout(7, 1));
        column.add(buildRegisterDisplay("PC", textFields, buttons));
        column.add(buildRegisterDisplay("MAR", textFields, buttons));
        column.add(buildRegisterDisplay("MBR", textFields, buttons));
        column.add(buildRegisterDisplay("IR", textFields, buttons));
        column.add(buildRegisterDisplay_NoButton("MFR", textFields));
        column.add(buildRegisterDisplay_NoButton("Privileged", textFields));
        column.add(buildRegisterDisplay_NoButton("CC (OUED)", textFields));

        column.setVisible(true);
        return column;
    }

    Box.Filler buildFiller() {

        Dimension minSize = new Dimension(50, 100);
        Dimension prefSize = new Dimension(50, 100);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);
        return new Box.Filler(minSize, prefSize, maxSize);
    }

    public JPanel buildRegisterDisplay(HashMap<String, JTextField> textFields, HashMap<String, JButton> buttons) {
        JPanel regDisplay = new JPanel();
        BoxLayout layout = new BoxLayout(regDisplay, BoxLayout.X_AXIS);
        regDisplay.setLayout(layout);


        regDisplay.add(buildLeftColumn(textFields, buttons));
        regDisplay.add(buildFiller());
        regDisplay.add(buildCenterColumn(textFields, buttons));
        regDisplay.add(buildFiller());
        regDisplay.add(buildRightColumn(textFields, buttons));

        regDisplay.setVisible(true);
        return regDisplay;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    void setupUserInputTextFields(JTextField octalInput, JTextField binInput) {
        // Set up octal
        octalInput.setEditable(true);
        octalInput.setText("");
        Dimension textFieldSize = new Dimension(200, 20);
        octalInput.setSize(textFieldSize);
        octalInput.setPreferredSize(textFieldSize);
        octalInput.setMaximumSize(textFieldSize);
        octalInput.setMinimumSize(textFieldSize);

        octalInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String newText = octalInput.getText();
                if (!Utility.isValidOctal(newText)) {
                    octalInput.setBackground(Color.PINK);
                    binInput.setText("");
                    return;
                }
                // Else it's a valid number
                octalInput.setBackground(Color.WHITE);
                binInput.setText(Utility.octalStringToBinaryString(newText));

            }
        });

        // Set up bin
        binInput.setEditable(true);
        binInput.setText("");
        binInput.setSize(textFieldSize);
        binInput.setPreferredSize(textFieldSize);
        binInput.setMaximumSize(textFieldSize);
        binInput.setMinimumSize(textFieldSize);

        binInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                String newText = binInput.getText();
                if (!Utility.isValidBinary(newText)) {
                    binInput.setBackground(Color.PINK);
                    octalInput.setText("");
                    return;
                }
                // Else it's a valid number
                binInput.setBackground(Color.white);
                octalInput.setText(Utility.binaryStrToOctalStr(newText));
            }
        });
    }

    JPanel buildUserInput(String labelName, JTextField inputField) {

        JPanel inputGroup = new JPanel();
        inputGroup.setLayout(new FlowLayout(FlowLayout.LEADING));
        //regDisplayGroup.setLayout(new GridLayout(1, 3));

        JLabel inputLabel = new JLabel(labelName);
        inputLabel.setSize(4, 1);
        inputGroup.add(inputLabel);

        inputGroup.add(inputField);

        inputGroup.setVisible(true);
        return inputGroup;
    }

    JPanel buildInputColumn(HashMap<String, JTextField> textFields) {
        JPanel column = new JPanel(new GridLayout(4, 1));

        JTextField octalInput = textFields.get("OctInput");
        JTextField binInput = textFields.get("BinInput");

        column.add(buildUserInput("Octal Input: ", octalInput));
        column.add(buildUserInput("Binary Input:", binInput));

        setupUserInputTextFields(octalInput, binInput);

        column.setVisible(true);
        return column;
    }

    JPanel buildButtonColumn(HashMap<String, JButton> buttons) {
        JPanel buttonColumn = new JPanel(new GridLayout(4, 2));

        buttonColumn.add(buttons.get("Load"));
        buttonColumn.add(buttons.get("Run"));
        buttonColumn.add(buttons.get("Load+"));
        buttonColumn.add(buttons.get("Step"));

        buttonColumn.add(buttons.get("Store"));
        buttonColumn.add(buttons.get("Halt"));
        buttonColumn.add(buttons.get("Store+"));

        buttonColumn.setVisible(true);
        return buttonColumn;
    }

    public JPanel buildUserInput(HashMap<String, JTextField> textFields, HashMap<String, JButton> buttons) {
        JPanel row = new JPanel(new FlowLayout());

        row.add(buildInputColumn(textFields));
        row.add(buildFiller());
        row.add(buildButtonColumn(buttons));

        row.setVisible(true);
        return row;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public JPanel buildIplSection(HashMap<String, JTextField> textFields, HashMap<String, JButton> buttons) {
        JPanel result = new JPanel();

        result.add(new JLabel("  .bi file"));


        JTextField pathToFile = textFields.get("IPL");
        pathToFile.setEditable(false); // read only
        Dimension textFieldSize = new Dimension(800, 20);
        pathToFile.setSize(textFieldSize);
        pathToFile.setPreferredSize(textFieldSize);
        pathToFile.setMaximumSize(textFieldSize);
        pathToFile.setMinimumSize(textFieldSize);
        result.add(pathToFile);

        result.add(buttons.get("IPL"));

        result.setVisible(true);
        return result;
    }

}
