package lib;

import java.util.ArrayList;

abstract public class Expression {

    public enum Type {
        THING,
        COLON,
        READ,
        READLIST;

        @Override
        public String toString() {
            switch (this) {
                case THING: return "thing";
                case COLON: return ":word";
                case READ: return "read";
                case READLIST: return "readlist";
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


    protected Expression(Type type, ArrayList<MUAObject> arglist) {
        this.type = type;
        this.arglist = arglist;
    }
    abstract public MUAObject eval(Scope scope);

    protected Type type;
    protected ArrayList<MUAObject> arglist;

}
