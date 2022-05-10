package item;

import java.util.Objects;

public class ILong {
    private final long value;

    public ILong(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ILong)) return false;
        ILong iLong = (ILong) o;
        return getValue() == iLong.getValue();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
