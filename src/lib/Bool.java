package lib;

import lib.MUAObject;

public class Bool extends MUAObject {
    // ctor
    public Bool(boolean b) {
        super(MUAObject.Type.BOOL);
        this.value = b;
    }

    @Override
    public Boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (value)
            return "true";
        else
            return "false";
    }

    // data members
    private boolean value;
}
