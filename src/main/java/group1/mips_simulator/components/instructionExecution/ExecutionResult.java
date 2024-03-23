package group1.mips_simulator.components.instructionExecution;

public class ExecutionResult {
    public final short newPC;
    public final boolean shouldContinue;

    public ExecutionResult(int newPC_) {
        this(newPC_, true);
    }
    public ExecutionResult(int newPC_, boolean shouldContinue) {
        this((short) newPC_, shouldContinue);
    }

    public ExecutionResult(short newPC_) {
        this(newPC_, true);
    }

    public ExecutionResult(short newPC_, boolean shouldContinue_) {
        newPC = newPC_;
        shouldContinue = shouldContinue_;
    }
}
