package lib;
import java.util.ArrayList;

abstract public class Statement {
    enum Type {
        MAKE
    }

    public Type getType() {
        return type;
    }
    protected Statement(Type type, ArrayList<MUAObject> arglist) {
        this.type = type;
        this.arglist = arglist;
    }
    abstract public void exec(Scope scope);


    protected Type type;
    protected ArrayList<MUAObject> arglist;

}
