package lib.operation;

import lib.*;
import lib.MuaObject;
import lib.Number;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class OpInt extends Expr {
    @Override
    public String getOpName() {
        return "int";
    }

    @Override
    public Word eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        MuaObject obj = (MuaObject) arglist.get(0);
        int i = (int)(Math.floor(((Number)obj).getValue()));
        return new Word(i);
    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            Number.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }
}
