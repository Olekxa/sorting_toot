package utils;

public enum SortType {
    NATURAL("natural"),
    BY_COUNT("byCount"),
    NONE("");

    private final String name;

    SortType(String name) {
        this.name = name;
    }

    public static SortType getType(String str) {
        for (SortType value : values()) {
            if (value.name.equals(str)) {
                return value;
            }
        }
        return null;
    }
}
