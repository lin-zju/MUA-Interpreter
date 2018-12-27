package lib.operation;

import lib.*;
import lib.util.ArgUtil;

import lib.Number;
import java.util.ArrayList;
import java.util.Arrays;

public class OpIsnumber extends Expr {
    @Override
    public String getOpName() {
        return "isnumber";
    }

    @Override
    public Word eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        MuaObject obj = (MuaObject) arglist.get(0);
        if (ArgUtil.typeCast(Number.class, obj) == null) {
            return new Word(false);
        }
        else
            return new Word(true);
    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            MuaObject.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }
}
