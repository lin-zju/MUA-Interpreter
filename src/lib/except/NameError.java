package lib.except;

import lib.MUAObject;

public class NameError extends MUAExcept {
    public NameError(String s) {
        super(s);
    }
    @Override
    public String getMessage() {
        return "NameError: " + super.getMessage();
    }
}
