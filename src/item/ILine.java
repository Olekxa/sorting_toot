package item;

import java.util.Objects;

public class ILine {
    private final String value;

    public ILine(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ILine iLine)) return false;
        return getValue().equals(iLine.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
