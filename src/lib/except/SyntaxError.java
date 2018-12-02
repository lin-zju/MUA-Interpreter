package lib.except;

public class SyntaxError extends MUAExcept {
    public SyntaxError(String s) {
        super(s);
    }
    @Override
    public String getMessage() {
        return "SyntaxError: " + super.getMessage();
    }
}
