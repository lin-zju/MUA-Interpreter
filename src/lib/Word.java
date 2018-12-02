package lib;

public class Word extends MUAObject {
    public Word(String str) {
        super(MUAObject.Type.WORD);
        this.value = str;
    }

    @Override
    public String toString() {
        return "\"" + value;
    }

    @Override
    public String getValue() {
        return value;
    }

    private String value;
}
