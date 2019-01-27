package lib.operation;

import lib.*;
import lib.util.ArgUtil;
import lib.util.RunUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class OpIf extends Expr {

    @Override
    public String getOpName() {
        return "if";
    }

    @Override
    public None eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        Bool cond = (Bool) arglist.get(0);
        List listA = (List) arglist.get(1);
        List listB = (List) arglist.get(2);
        if (cond.getValue()) {
            RunUtil.runList(scope, listA);
        }
        else {
            RunUtil.runList(scope, listB);
        }

        return new None();

    }

    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            Bool.class,
            List.class,
            List.class
    ));
    public int getArgNum() {
        return argtypes.size();
    }
}
