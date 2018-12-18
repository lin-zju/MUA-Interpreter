package lib.operation.operator;

import lib.Bool;
import lib.Expr;
import lib.Scope;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Not extends Expr {

    @Override
    public String getOpName() {
        return "not";
    }

    @Override
    public Bool eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        Bool a = (Bool) arglist.get(0);
        return new Bool(!a.getValue());
    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            Bool.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }
}
