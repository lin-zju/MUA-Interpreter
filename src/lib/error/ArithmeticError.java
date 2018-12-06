package lib.error;

public class ArithmeticError extends MUAError {

    public ArithmeticError(String s) {
        super(s);
    }

    public String getMessage() {
        return "Arithmetic Error: " + super.getMessage();
    }
}
