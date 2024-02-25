package group1.mips_simulator.FrontEnd;

import group1.mips_simulator.SimController;
import group1.mips_simulator.components.Computer;

public class Redraw {

    public void updateFrontEnd(SimController frontEnd, Computer model) {
        // Update all the GPRs
        frontEnd.GPR0.setText(model.cpu.regfile.getGPR(0).value.toString_Binary());
        frontEnd.GPR1.setText(model.cpu.regfile.getGPR(1).value.toString_Binary());
        frontEnd.GPR2.setText(model.cpu.regfile.getGPR(2).value.toString_Binary());
        frontEnd.GPR3.setText(model.cpu.regfile.getGPR(3).value.toString_Binary());

        // Update all IXRs
        frontEnd.IXR1.setText(model.cpu.regfile.getIXR(1).value.toString_Binary());
        frontEnd.IXR2.setText(model.cpu.regfile.getIXR(2).value.toString_Binary());
        frontEnd.IXR3.setText(model.cpu.regfile.getIXR(3).value.toString_Binary());

        // Update other registers
        frontEnd.PC.setText(model.cpu.regfile.getPC().value.toString_Binary());
        frontEnd.MAR.setText(model.cpu.regfile.getMAR().value.toString_Binary());
        frontEnd.MBR.setText(model.cpu.regfile.getMBR().value.toString_Binary());
        frontEnd.RI.setText(model.cpu.regfile.getIR().value.toString_Binary());
        frontEnd.MFR.setText(model.cpu.regfile.getMFR().value.toString_Binary());
        frontEnd.CC.setText(model.cpu.regfile.getCC().value.toString_Binary());
    }
}
