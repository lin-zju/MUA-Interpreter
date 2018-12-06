package lib.error;

public class ArgError extends MUAError {

    public ArgError(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return "ArgError: " + super.getMessage();
    }
}
