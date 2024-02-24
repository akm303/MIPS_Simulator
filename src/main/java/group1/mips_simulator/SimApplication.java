package group1.mips_simulator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SimApplication extends Application {

    static final int WIDTH = 900;
    static final int HEIGHT = 750;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);

//        HBox hBox_fullRegDisplay = new HBox();
//        VBox vBox_leftColumnRegs = new VBox();
//        vBox_leftColumnRegs.getChildren().add(makeRegisterAndLabel("left", "0000000000000000"));
//
//        VBox vBox_middleColumnRegs = new VBox();
//        vBox_middleColumnRegs.getChildren().add(makeRegisterAndLabel("Middle", "0000000000000000"));
//
//        VBox vBox_rightColumnRegs = new VBox();
//        vBox_rightColumnRegs.getChildren().add(makeRegisterAndLabel("PC", "000000000000"));
//
//        hBox_fullRegDisplay.getChildren().addAll(vBox_leftColumnRegs, vBox_middleColumnRegs, vBox_rightColumnRegs);


        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public HBox makeRegisterAndLabel(String registerName, String initialValue) {
        Label label1 = new Label(registerName + ":");
        TextField textField = new TextField(initialValue);

        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField);
        hb.setSpacing(10);

        return hb;
    }

    public static void main(String[] args) {
        launch();
    }
}
