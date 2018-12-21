package lib;

import lib.error.SyntaxError;
import lib.util.ArgUtil;

import java.util.ArrayList;

abstract public class Expr extends MUAObject {

//    public enum SubType {
//        MAKE,
//        ERASE,
//        PRINT,
//        READLIST,
//        THING,
//        ISNAME,
//        READ,
//        REPEAT,
//        ADD, SUB, MUL, DIV, MOD,
//        EQ, GT, LT,
//        AND, OR, NOT,
//
//        FUNC,
//        BASIC;
//
//        @Override
//        public String toString() {
//            switch (this) {
//                case MAKE: return "make";
//                case ERASE: return "erase";
//                case PRINT: return "print";
//                case READLIST: return "readlist";
//                case THING: return "thing";
//                case ISNAME: return "isname";
//                case READ: return "read";
//                case REPEAT: return "repeat";
//                case FUNC: return "func";
//                case ADD: return "add";
//                case SUB: return "sub";
//                case EQ: return "eq";
//                case GT: return "gt";
//                case AND: return "and";
//                case OR: return "or";
//                case NOT: return "not";
//            }
//            return "UNKNOWN";
//        }
//    }


    @Override
    public String getTypeString() {
        return "expr";
    }

    // get operation name
    abstract public String getOpName();

    // evaluation
    public MUAObject eval(Scope scope) throws Exception {
        ArgUtil.evalAll(arglist, scope);
        for (MUAObject obj : arglist) {
            if (obj instanceof None)
                throw new SyntaxError("operation without a return value cannot be used as an argument");

        }
        return new None();
    }

    // argument list
    public void setArglist(ArrayList<MUAObject> arglist) {
        this.arglist = arglist;
    }

    // correct argument count
    abstract public int getArgNum();


    @Override
    public Expr getValue() {
        return this;
    }

    @Override
    public String toString() {
        String temp = getOpName();
        for (MUAObject arg: arglist) {
            temp += " " + arg.toString();
        }
        return temp;
    }


    protected ArrayList<MUAObject> arglist = new ArrayList<>();

}
