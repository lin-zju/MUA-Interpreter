package lib.error;


public class InputError extends MUAError {
    public InputError(String s) {
        super(s);
    }

    public String getMessage() {
        return "Input Error: " + super.getMessage();
    }
}

