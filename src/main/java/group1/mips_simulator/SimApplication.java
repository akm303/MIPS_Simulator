package group1.mips_simulator;

import group1.mips_simulator.components.Computer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SimApplication extends Application {

    static final int WIDTH = 900;
    static final int HEIGHT = 550;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SimApplication.class.getResource("hello-view.fxml"));

        SimController controller = new SimController(new Computer());
        fxmlLoader.setController(controller);
        Scene scene = new Scene(fxmlLoader.load(), WIDTH, HEIGHT);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
