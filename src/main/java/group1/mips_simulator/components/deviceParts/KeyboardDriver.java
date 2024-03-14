package group1.mips_simulator.components.deviceParts;

import group1.mips_simulator.components.Config;

public class KeyboardDriver implements InDevice, DeviceDriver {

    public static final String NAME = "Keyboard";

    @Override
    public char readCharacter() {
        throw new IllegalArgumentException("Not implemented yet");
    }

    @Override
    public String name() {
        return NAME;
    }

    @Override
    public int DeviceId() {
        return Config.CONSOLE_KEYBOARD_DEVID;
    }

}
