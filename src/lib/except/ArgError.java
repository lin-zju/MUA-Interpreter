package lib.except;

public class ArgError extends MUAExcept {

    public ArgError(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return "ArgError: " + super.getMessage();
    }
}
