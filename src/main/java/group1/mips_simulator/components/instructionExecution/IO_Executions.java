package group1.mips_simulator.components.instructionExecution;

import group1.mips_simulator.Utility;
import group1.mips_simulator.components.Computer;
import group1.mips_simulator.components.Config;
import group1.mips_simulator.components.cpuParts.Register;
import group1.mips_simulator.components.deviceParts.InDevice;
import group1.mips_simulator.components.deviceParts.OutDevice;
import group1.mips_simulator.components.instructionParts.instruction.IO_Instruction;

public class IO_Executions {

    /**
     * IN r, devid
     * op code: 32(octal)
     * Input Character To Register from Device, r = 0..3
     */
    public ExecutionResult execute_in(Computer computer, IO_Instruction i) {
        Register targetReg = computer.cpu.regfile.getGPR(i.getRegister().value);
        short targetDeviceID = i.getDeviceID().value;

        // Validate device
        if (!Config.IN_DEVICES.contains((int) targetDeviceID)) {
            // If the target devices is NOT in the list of IN devices
            throw new IllegalArgumentException("Attempted to run an IN instruction on an invalid device id target: " + targetDeviceID);
        }
        // Else target is valid
        InDevice targetDevice = (InDevice) computer.getDriver(Config.CONSOLE_KEYBOARD_DEVID);
        char fromDevice = targetDevice.readCharacter();

        // Put the character into the target register
        targetReg.write(Utility.charToShort(fromDevice));

        return new ExecutionResult(computer.currentPcPlus1());
    }

    /**
     * OUT r, devid
     * op code: 33(octal)
     * Output Character to Device from Register, r = 0..3
     */
    public ExecutionResult execute_out(Computer computer, IO_Instruction i) {
        Register regWithChar = computer.cpu.regfile.getGPR(i.getRegister().value);
        short targetDeviceID = i.getDeviceID().value;

        // Validate device
        if (!Config.OUT_DEVICES.contains((int) targetDeviceID)) {
            // If the target devices is NOT in the list of OUT devices
            throw new IllegalArgumentException("Attempted to run an OUT instruction on an invalid device id target: " + targetDeviceID);
        }
        // Else target is valid
        OutDevice targetDevice = (OutDevice) computer.getDriver(Config.CONSOLE_KEYBOARD_DEVID);

        // Get the character from the register
        char outputCharacter = Utility.shortToChar(regWithChar.read());

        // Write character out to device
        targetDevice.writeCharacter(outputCharacter);

        return new ExecutionResult(computer.currentPcPlus1());
    }

}
