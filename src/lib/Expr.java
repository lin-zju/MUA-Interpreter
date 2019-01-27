package lib;

import lib.error.SyntaxError;

import java.util.ArrayList;

abstract public class Expr extends MuaObject {

    @Override
    public String getTypeString() {
        return "expr";
    }

    // get operation name
    abstract public String getOpName();

    // evaluation
    public MuaObject eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
//        ArgUtil.evalAll(arglist, scope);
        for (MuaObject obj : arglist) {
            if (obj instanceof None)
                throw new SyntaxError("operation without a return value cannot be used as an argument");

        }
        return new None();
    }
    abstract public int getArgNum();


    @Override
    public Expr getValue() {
        return this;
    }

    @Override
    public String toString() {
        return getOpName();
    }

}
