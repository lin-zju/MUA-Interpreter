package lib;

import lib.except.MUAExcept;
import lib.MUAObject;
import lib.util.ArgUtil;

import java.util.ArrayList;

abstract public class Expr extends MUAObject {

    public enum SubType {
        MAKE,
        ERASE,
        PRINT,
        READLIST,
        THING,
        ISNAME,
        READ,
        REPEAT,
        FUNC,
        Basic;


        @Override
        public String toString() {
            switch (this) {
                case MAKE: return "make";
                case ERASE: return "erase";
                case PRINT: return "print";
                case READLIST: return "readlist";
                case THING: return "thing";
                case ISNAME: return "isname";
                case READ: return "read";
                case REPEAT: return "repeat";
                case FUNC: return "func";
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


    protected Expr(SubType subtype, ArrayList<MUAObject> arglist,
                   ArrayList<MUAObject.Type> typelist) {
        super(MUAObject.Type.EXPR);
        this.subtype = subtype;
        this.arglist = arglist;
        this.typelist = typelist;
    }
    public MUAObject eval(Scope scope) throws Exception {
        ArgUtil.evalAll(arglist, scope);
        ArgUtil.argCheck(getName(), typelist, arglist);
        return new None();
    }

    @Override
    public Expr getValue() {
        return this;
    }

    @Override
    public String toString() {
        String temp = getName();
        for (MUAObject arg: arglist) {
            temp += " " + arg.toString();
        }
        return temp;
    }


    protected SubType subtype;
    protected ArrayList<MUAObject> arglist;
    protected ArrayList<MUAObject.Type> typelist;

}
