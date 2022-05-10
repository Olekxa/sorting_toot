package engine;

import data.Data;
import utils.SortType;

public abstract class Command<E extends Data<?>> {
    private final E input;
    private final SortType sortType;

    public Command(E input, SortType sortType) {
        this.input = input;
        this.sortType = sortType;
    }

    public abstract String execute();

    protected E getInput() {
        return input;
    }

    protected SortType getSortType() {
        return sortType;
    }
}