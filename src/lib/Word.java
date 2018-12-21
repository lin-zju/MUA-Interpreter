package lib;

public class Word extends MUAObject {
    public Word(double d) {
        this.value = Double.toString(d);
    }

    public Word(boolean b) {
        this.value = Boolean.toString(b);
    }
    public Word(String str) {
        this.value = str;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public String getTypeString() {
        return "word";
    }

    @Override
    public String getValue() {
        return value;
    }

    public Number toNumber() {
        try {
            Double d = Double.valueOf(value);
            return new Number(d);
        }
        catch (NumberFormatException e) {
            return null;
        }
    }

    public Bool toBool() {
        if (value.equals("true")) {
            return new Bool(true);
        }
        else if (value.equals("false")) {
            return new Bool(false);
        }
        else
            return null;
    }

    private String value;
}
