module group1.mips_simulator {
    requires javafx.controls;
    requires javafx.fxml;


    opens group1.mips_simulator to javafx.fxml;
    exports group1.mips_simulator;
    exports group1.mips_simulator.FrontEnd;
}