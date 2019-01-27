package lib.error;

public class TypeError extends MuaError {

    public TypeError(String s) {
        super(s);
    }

    @Override
    public String getMessage() {
        return "TypeError: " + super.getMessage();
    }
}
