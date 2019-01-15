package lib.error;

public class IOError extends MuaError{
    public IOError(String s) {
        super(s);
    }

    public String getMessage() {
        return "IOError: " + super.getMessage();
    }
}
