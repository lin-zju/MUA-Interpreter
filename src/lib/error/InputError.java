package lib.error;


public class InputError extends MuaError {
    public InputError(String s) {
        super(s);
    }

    public String getMessage() {
        return "InputError: " + super.getMessage();
    }
}

