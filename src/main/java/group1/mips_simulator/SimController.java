package group1.mips_simulator;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class SimController {
    //region Binary Displays

    //region Left Column (General Purpose Registers)

    public VBox VBoxLeft_GPRs;
    public TextField GPR0;
    public TextField GPR1;
    public TextField GPR2;
    public TextField GPR3;


    public void gpr0OnClick(ActionEvent actionEvent) {
    }

    public void gpr1OnClick(ActionEvent actionEvent) {
    }

    public void gpr2OnClick(ActionEvent actionEvent) {
    }

    public void gpr3OnClick(ActionEvent actionEvent) {
    }

    //endregion

    //region Middle Column (Index Registers)
    public VBox VBoxCenter_IXRs;
    public TextField IXR3;
    public TextField IXR2;
    public TextField IXR1;

    public void ixr1OnClick(ActionEvent actionEvent) {
    }

    public void ixr2OnClick(ActionEvent actionEvent) {
    }

    public void ixr3OnClick(ActionEvent actionEvent) {
    }


    // endregion

    //region Right Column (Other Registers)
    public VBox VBoxRight_OtherRegs;
    public TextField PC;
    public TextField MAR;
    public TextField MBR;
    public TextField RI;
    public TextField MFR;
    public TextField Privileged;
    public TextField CC;


    public void riOnClick(ActionEvent actionEvent) {
    }

    public void mbrOnClick(ActionEvent actionEvent) {
    }

    public void marOnClick(ActionEvent actionEvent) {
    }

    public void pcOnClick(ActionEvent actionEvent) {
    }

    //endregion

    //region Command Buttons

    public void loadOnClick() {
    }

    public void loadPlusOnClick(ActionEvent actionEvent) {
    }

    public void storeOnClick(ActionEvent actionEvent) {
    }

    public void storePlusOnClick(ActionEvent actionEvent) {
    }

    public void runOnClick(ActionEvent actionEvent) {
    }

    public void stepOnClick(ActionEvent actionEvent) {
    }

    public void haltOnClick(ActionEvent actionEvent) {
    }

    //endregion


    public HBox hbox_UserInput;
    public TextField BinInput;
    public TextField OctalInput;

    //region Load File
    public TextField FileToLoad;

    public void iplOnClick(ActionEvent actionEvent) {
    }

    //endregion

    //endregion

}