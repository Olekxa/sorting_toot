package data;

public enum DataType {
    LONG("long"),
    WORD("word"),
    LINE("line");

    private final String nameCommand;


    DataType(String nameCommand) {
        this.nameCommand = nameCommand;
    }

    public static DataType getDataType(String str) {
        for (DataType value : values()) {
            if (value.nameCommand.equals(str)) {
                return value;
            }
        }
        return null;
    }
}
