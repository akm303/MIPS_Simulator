module group1.mips_simulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens group1.mips_simulator to javafx.fxml;
    exports group1.mips_simulator;
    exports group1.mips_simulator.FrontEnd;
    exports group1.mips_simulator.FrontEnd.Archive;
}