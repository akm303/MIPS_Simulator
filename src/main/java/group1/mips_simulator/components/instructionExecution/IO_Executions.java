package group1.mips_simulator.components.instructionExecution;

import group1.mips_simulator.components.Computer;
import group1.mips_simulator.components.cpuParts.Register;
import group1.mips_simulator.components.deviceParts.InDevice;
import group1.mips_simulator.components.instructionParts.instruction.IO_Instruction;

import java.util.HashSet;
import java.util.Set;

public class IO_Executions {
    public static final int CONSOLE_KEYBOARD_DEVID = 0;
    public static final int CONSOLE_PRINTER_DEVID = 1;
    public static final int CARD_READER_DEVID = 2;

    public static final Set<Integer> IN_DEVICES = new HashSet<>() {{
        add(CONSOLE_KEYBOARD_DEVID);
        add(CARD_READER_DEVID);
    }};

    public static final Set<Integer> OUT_DEVICES = new HashSet<>() {{
        add(CONSOLE_PRINTER_DEVID);
    }};

    /**
     * IN r, devid
     * op code: 32(octal)
     * Input Character To Register from Device, r = 0..3
     */
    public ExecutionResult execute_in(Computer computer, IO_Instruction i) {
        Register targetReg = computer.cpu.regfile.getGPR(i.getRegister().value);
        short targetDeviceID = i.getDeviceID().value;

        // Validate device
        if (!IN_DEVICES.contains((int) targetDeviceID)) {
            // If the target devices is NOT in the list of IN devices
            throw new IllegalArgumentException("Attempted to run an IN instruction on an invalid device id target: " + targetDeviceID);
        }
        // Else target is valid
        InDevice targetDevice;
        char fromDevice = targetDevice.readCharacter();


        return new ExecutionResult(computer.currentPcPlus1());
    }
}
