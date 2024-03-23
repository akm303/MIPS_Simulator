package group1.mips_simulator.FrontEnd;

import javax.swing.*;
import java.awt.*;

public class SwingFactory {

    JPanel buildRegisterDisplay(String registerName) {

        JPanel regDisplayGroup = new JPanel();
        regDisplayGroup.setLayout(new FlowLayout(FlowLayout.TRAILING));
        //regDisplayGroup.setLayout(new GridLayout(1, 3));

        JLabel regLabel = new JLabel(registerName);
        regLabel.setSize(4, 1);
        regDisplayGroup.add(regLabel);

        JTextField regContents = new JTextField();
        regContents.setEditable(false); // read only
        regContents.setText("0000_0000_0000_0000");
        //regContents.setMinimumSize(new Dimension(50, 20));
        regDisplayGroup.add(regContents);

        JButton button = new JButton();
        Dimension buttonSize = new Dimension(20, 20);
        button.setSize(buttonSize);
        button.setPreferredSize(buttonSize);
        button.setMaximumSize(buttonSize);
        button.setMinimumSize(buttonSize);
        regDisplayGroup.add(button);

        regDisplayGroup.setVisible(true);
        return regDisplayGroup;
    }

    JPanel buildLeftColumn() {
        JPanel leftColumn = new JPanel(new GridLayout(7, 1));
        leftColumn.add(buildRegisterDisplay("GPR 0:"));
        leftColumn.add(buildRegisterDisplay("GPR 1:"));
        leftColumn.add(buildRegisterDisplay("GPR 2:"));
        leftColumn.add(buildRegisterDisplay("GPR 3:"));

        leftColumn.setVisible(true);
        return leftColumn;
    }

    JPanel buildCenterColumn() {
        JPanel column = new JPanel(new GridLayout(7, 1));
        column.add(buildRegisterDisplay("IXR 1:"));
        column.add(buildRegisterDisplay("IXR 2:"));
        column.add(buildRegisterDisplay("IXR 3:"));

        column.setVisible(true);
        return column;
    }

    JPanel buildRightColumn() {
        JPanel column = new JPanel(new GridLayout(7, 1));
        column.add(buildRegisterDisplay("PC:"));
        column.add(buildRegisterDisplay("MAR:"));
        column.add(buildRegisterDisplay("MBR:"));
        column.add(buildRegisterDisplay("IR:"));
        column.add(buildRegisterDisplay("MFR:"));
        column.add(buildRegisterDisplay("Privileged:"));
        column.add(buildRegisterDisplay("CC (OUED):"));

        column.setVisible(true);
        return column;
    }

    Box.Filler buildFiller() {

        Dimension minSize = new Dimension(50, 100);
        Dimension prefSize = new Dimension(50, 100);
        Dimension maxSize = new Dimension(Short.MAX_VALUE, 100);
        return new Box.Filler(minSize, prefSize, maxSize);
    }

    public JPanel buildRegisterDisplay() {
        JPanel regDisplay = new JPanel();
        BoxLayout layout = new BoxLayout(regDisplay, BoxLayout.X_AXIS);
        regDisplay.setLayout(layout);


        regDisplay.add(buildLeftColumn());
        regDisplay.add(buildFiller());
        regDisplay.add(buildCenterColumn());
        regDisplay.add(buildFiller());
        regDisplay.add(buildRightColumn());

        regDisplay.setVisible(true);
        return regDisplay;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

//    public JPanel buildUserInput() {
//
//    }

}
