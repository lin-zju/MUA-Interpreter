package lib.error;

public class NameError extends MuaError {
    public NameError(String s) {
        super(s);
    }
    @Override
    public String getMessage() {
        return "NameError: " + super.getMessage();
    }
}
