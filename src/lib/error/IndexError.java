package lib.error;

import lib.MuaObject;

public class IndexError extends MuaError {
    public IndexError(String s) {
        super(s);
    }

    public String getMessage() {
        return "IndexError: " + super.getMessage();
    }
}
