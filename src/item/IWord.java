package item;

import java.util.Objects;

public class IWord {
    private final String value;

    public IWord(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int length() {
        return value.length();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IWord iWord)) return false;
        return getValue().equals(iWord.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
