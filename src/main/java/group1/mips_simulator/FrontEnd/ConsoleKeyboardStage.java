package group1.mips_simulator.FrontEnd;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ConsoleKeyboardStage extends Stage {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 700;

    TextField inputField;
    Button doneButton;

    public ConsoleKeyboardStage() {
        inputField = new TextField();

        doneButton = new Button();
        doneButton.setText("Done");

        HBox hbox = new HBox();
        hbox.getChildren().setAll(inputField, doneButton);

        this.setScene(new Scene(hbox));
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        this.setX(0);
        this.setTitle("Console Keyboard");
        this.show();
    }

    public String readFromKeyboard() {
        return inputField.getText();
    }
}
