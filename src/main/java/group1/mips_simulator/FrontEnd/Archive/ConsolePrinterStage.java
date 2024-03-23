package group1.mips_simulator.FrontEnd.Archive;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Vector;

public class ConsolePrinterStage extends Stage {

    public static final int WIDTH = 700;
    public static final int HEIGHT = 400;

    public LogLevel currentLevel = LogLevel.DEBUG;

    ScrollPane scrollPane = new ScrollPane();
    VBox insideScrollPane = new VBox();

    Button debugButton = new Button("Debug");
    Button msgButton = new Button("Message");
    Button errorButton = new Button("Error");

    Vector<TxtAndLabel> labels = new Vector<>();
    boolean nextColor = false;

    protected static class TxtAndLabel {
        public String text;
        public LogLevel level;

        public TxtAndLabel(String t, LogLevel lv) {
            this.text = t;
            this.level = lv;
        }
    }

    public ConsolePrinterStage() {
        HBox buttonBox = new HBox();
        buttonBox.getChildren().addAll(debugButton, msgButton, errorButton);
        buttonBox.setSpacing(10);
        errorButton.setOnAction(this::errorOnClick);
        msgButton.setOnAction(this::msgOnClick);
        debugButton.setOnAction(this::debugOnClick);
        this.debugOnClick(new ActionEvent()); // Initial state of logging is debug

        VBox vbox = new VBox();
        vbox.getChildren().addAll(buttonBox, scrollPane);
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
        scrollPane.setContent(insideScrollPane);
        scrollPane.setMinWidth(WIDTH);
        insideScrollPane.setMinWidth(WIDTH);

        this.setScene(new Scene(vbox));
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        this.setX(Screen.getPrimary().getBounds().getWidth() - WIDTH);
        this.setTitle("Console Printer");
        this.show();

        // TODO: Remove these
        this.addLabel("DEBUG", LogLevel.DEBUG);
        this.addLabel("DEBUG", LogLevel.DEBUG);
        this.addLabel("DEBUG", LogLevel.DEBUG);
        this.addLabel("DEBUG", LogLevel.DEBUG);

        this.addLabel("MSG", LogLevel.MESSAGE);
        this.addLabel("MSG", LogLevel.MESSAGE);
        this.addLabel("MSG", LogLevel.MESSAGE);
        this.addLabel("MSG", LogLevel.MESSAGE);

        this.addLabel("ERROR", LogLevel.ERROR);
        this.addLabel("ERROR", LogLevel.ERROR);
        this.addLabel("ERROR", LogLevel.ERROR);
        this.addLabel("ERROR", LogLevel.ERROR);
    }

    public void addLabel(String newText, LogLevel level) {
        // Always log the message to the terminal console (Not the Console Printer)
        System.out.println(newText);

        TxtAndLabel tal = new TxtAndLabel(newText, level);
        labels.add(tal);
        addLabel_Internal(tal);
    }

    protected void addLabel_Internal(TxtAndLabel tal) {
        // First check if our current log level allows us to print this
        switch (tal.level) {
            case ERROR -> {
                // Error always prints
            }
            case MESSAGE -> {
                // Only print if the current log level is not ERROR
                if (currentLevel == LogLevel.ERROR) {
                    return;
                }
            }
            case DEBUG -> {
                // Only prints if the current leve is DEBUG
                if (currentLevel == LogLevel.ERROR || currentLevel == LogLevel.MESSAGE) {
                    return;
                }
            }
            default -> throw new IllegalStateException("Unexpected value: " + tal.level);
        }

        insideScrollPane.getChildren().add(makeLabel(tal.text));
    }

    protected Label makeLabel(String newText) {
        Label newLabel = new Label();
        newLabel.setText(newText);

        // Other style stuff
        newLabel.setMinWidth(WIDTH);
        String color = nextColor ?
                "lightgrey" :
                "white";
        nextColor = !nextColor;
        newLabel.setStyle("-fx-background-color: " + color);

        return newLabel;
    }


    protected void resetButtonColor() {
        this.debugButton.setStyle("-fx-background-color: white");
        this.msgButton.setStyle("-fx-background-color: white");
        this.errorButton.setStyle("-fx-background-color: white");
    }

    protected void filterOnCurrentLogLevel() {
        this.insideScrollPane.getChildren().clear();
        for (TxtAndLabel legacyMsg : this.labels) {
            // NOTE: addLabel_Internal(...) does NOT add elements to the this.labels field
            // Which is good! Otherwise, we'll get an infinite for loop.
            this.addLabel_Internal(legacyMsg);
        }
    }


    protected void debugOnClick(ActionEvent event) {
        this.currentLevel = LogLevel.DEBUG;
        resetButtonColor();
        this.debugButton.setStyle("-fx-background-color: lightblue");
        filterOnCurrentLogLevel();
    }

    protected void msgOnClick(ActionEvent event) {
        this.currentLevel = LogLevel.MESSAGE;
        resetButtonColor();
        this.msgButton.setStyle("-fx-background-color: lightblue");
        filterOnCurrentLogLevel();
    }

    protected void errorOnClick(ActionEvent event) {
        this.currentLevel = LogLevel.ERROR;
        resetButtonColor();
        this.errorButton.setStyle("-fx-background-color: lightblue");
        filterOnCurrentLogLevel();
    }
}
