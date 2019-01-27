package lib.error;

public class SyntaxError extends MuaError {
    public SyntaxError(String s) {
        super(s);
    }
    @Override
    public String getMessage() {
        return "SyntaxError: " + super.getMessage();
    }
}
