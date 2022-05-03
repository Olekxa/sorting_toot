package engine;

import data.InputData;

public abstract class Command {
    private final InputData inputData;

    public Command(InputData inputData) {
        this.inputData = inputData;
    }

    public InputData getInputData() {
        return this.inputData;
    }

    public abstract String execute();
}