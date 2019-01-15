package lib.operation;

import lib.*;
import lib.Number;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class OpWait extends Expr {
    @Override
    public String getOpName() {
        return "wait";
    }

    @Override
    public None eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        Number n = (Number) arglist.get(0);
        Thread.sleep(n.getValue().intValue());
        return new None();
    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            Number.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }
}
