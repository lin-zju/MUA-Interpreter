package lib;

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
        ADD, SUB, MUL, DIV, MOD,
        EQ, GT, LT,
        AND, OR, NOT,

        FUNC,
        BASIC;

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
                case ADD: return "add";
                case SUB: return "sub";
                case EQ: return "eq";
                case GT: return "gt";
                case AND: return "and";
                case OR: return "or";
                case NOT: return "not";
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


    protected Expr(SubType subtype) {
        super(MUAObject.Type.EXPR);
        this.subtype = subtype;
    }

    public MUAObject eval(Scope scope) throws Exception {
        ArgUtil.evalAll(arglist, scope);
//        ArgUtil.argCheck(getName(), typelist, arglist);
        return new None();
    }

    public void setArglist(ArrayList<MUAObject> arglist) {
        this.arglist = arglist;
    }

    abstract public int getArgNum();


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
    protected ArrayList<MUAObject> arglist = new ArrayList<>();
//    protected ArrayList<MUAObject.Type> typelist = new ArrayList<>();

}
