package lib.util;

import lib.*;
import lib.Number;

import java.util.ArrayList;
import java.util.Arrays;

import static lib.util.RunUtil.runList;

public class OpRun extends Expr {
    @Override
    public String getOpName() {
        return "run";
    }

    @Override
    public None eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        List l = (List) arglist.get(0);
        runList(scope, l);
        return new None();
    }

    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            List.class
    ));

    public int getArgNum() {
        return argtypes.size();

    }
}
