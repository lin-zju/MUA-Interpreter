package lib;
import lib.except.MUAExcept;

import java.util.ArrayList;

abstract public class Statement {
    public enum Type {
        MAKE,
        ERASE,
        PRINT,
        REPEAT,
        EXPR;

        @Override
        public String toString() {
            switch (this) {
                case MAKE: return "make";
                case ERASE: return "erase";
                case PRINT: return "print";
                case REPEAT: return "repeat";
                case EXPR: return "expr";
            }
            return "UNKNOWN";
        }
    }

    public Type getType() {
        return type;
    }

    public String getName() {
        return type.toString();
    }

    protected Statement(Type type, ArrayList<MUAObject> arglist) {
        this.type = type;
        this.arglist = arglist;
    }
    abstract public void exec(Scope scope) throws MUAExcept;


    protected Type type;
    protected ArrayList<MUAObject> arglist;

}
