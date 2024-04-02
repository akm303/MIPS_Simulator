package group1.mips_simulator.components.deviceParts;

import group1.mips_simulator.FrontEnd.SwingConsole;
import group1.mips_simulator.components.Config;

public class KeyboardDriver implements InDevice, DeviceDriver {

    public static final String NAME = "Keyboard";

    @Override
    public Character readCharacter() {
        //throw new IllegalArgumentException("Not implemented yet");
        return SwingConsole.keyboardController.getNextChar();
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
