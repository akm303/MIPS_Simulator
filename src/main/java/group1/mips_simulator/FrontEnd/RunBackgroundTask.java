package group1.mips_simulator.FrontEnd;

import group1.mips_simulator.components.Computer;

import javax.swing.*;
import java.util.List;

public class RunBackgroundTask extends SwingWorker<Void, String> {

    Computer computer;

    RunBackgroundTask(Computer computer_) {
        computer = computer_;
    }

    @Override
    protected Void doInBackground() throws Exception {
        boolean computerMayContinue = true;
        while (computerMayContinue) {
            System.out.println("BG Task Running Instruction");
            computerMayContinue = this.computer.runCurrentPC();
            publish();
        }
        SwingConsole.redraw();

        return null;
    }

    @Override
    protected void process(List<String> chunks) {
        // Update the UI
        SwingConsole.redraw();
    }

    @Override
    protected void done() {
        SwingConsole.redraw();
    }

    public void halt() {
        this.cancel(true);
    }

}
