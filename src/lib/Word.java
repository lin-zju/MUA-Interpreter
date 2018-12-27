package lib;

import java.nio.charset.IllegalCharsetNameException;

public class Word extends MuaObject {
    public Word(double d) {
        if (isInt(d)) {
            this.value = Integer.toString((int)d);
        }
        else {
            this.value = Double.toString(d);
        }
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

    private static boolean isInt(double d) {
        final double THRESHOLD = 1e-5;
        if ((d - Math.floor((d)) < THRESHOLD) || (Math.ceil(d)) - d < THRESHOLD)
            return true;
        else
            return false;
    }

    private String value;
}
