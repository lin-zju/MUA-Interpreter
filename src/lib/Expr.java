package lib;

import lib.except.MUAExcept;

import java.util.ArrayList;

abstract public class Expression {

    public enum SubType {
        MAKE,
        ERASE,
        PRINT,
        READLIST,
        THING,
        ISNAME,
        REPEAT;

        @Override
        public String toString() {
            switch (this) {
                case MAKE: return "make";
                case ERASE: return "erase";
                case PRINT: return "print";
                case READLIST: return "readlist";
                case THING: return "thing";
                case ISNAME: return "isname";
                case REPEAT: return "repeat";
            }
            return "UNKNOWN";
        }
    }

    public SubType getSubType() {
        return subtype;
    }

    public String getName() {
        return subtype.toString();
    }


    protected Expression(SubType subtype, ArrayList<MUAObject> arglist) {
        this.subtype = subtype;
        this.arglist = arglist;
    }
    abstract public MUAObject eval(Scope scope) throws MUAExcept;

    protected SubType subtype;
    protected ArrayList<MUAObject> arglist;

}
