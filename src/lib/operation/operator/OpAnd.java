package lib.operation.operator;

import lib.*;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class OpAnd extends Expr {
    @Override
    public String getOpName() {
        return "and";
    }

    @Override
    public Word eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        Bool a = (Bool) arglist.get(0);
        Bool b = (Bool) arglist.get(1);
        return new Word(a.getValue() && b.getValue());
    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            Bool.class,
            Bool.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }
}
