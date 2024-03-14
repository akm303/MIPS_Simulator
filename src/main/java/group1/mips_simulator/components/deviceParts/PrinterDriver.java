package group1.mips_simulator.components.deviceParts;

import group1.mips_simulator.components.Config;

public class PrinterDriver implements OutDevice, DeviceDriver {

    public static final String NAME = "Printer";

    @Override
    public void writeCharacter(char c) {
        // TODO:
        throw new IllegalArgumentException("Not implemented yet");
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public int DeviceId() {
        return Config.CONSOLE_PRINTER_DEVID;
    }
}
