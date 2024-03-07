package group1.mips_simulator.components.instructionExecution;

public class ExecutionResult {
    public final short newPC;
    public final boolean shouldHaltComputer;

    public ExecutionResult(int newPC_) {
        this(newPC_, false);
    }
    public ExecutionResult(int newPC_, boolean shouldHaltComputer) {
        this((short) newPC_, shouldHaltComputer);
    }

    public ExecutionResult(short newPC_) {
        this(newPC_, false);
    }

    public ExecutionResult(short newPC_, boolean shouldHaltComputer_) {
        newPC = newPC_;
        shouldHaltComputer = shouldHaltComputer_;
    }
}
