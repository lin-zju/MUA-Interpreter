package lib.operation;

import lib.Expr;
import lib.MuaObject;
import lib.None;
import lib.Scope;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class OpOutput extends Expr {
    @Override
    public String getOpName() {
        return "output";
    }

    @Override
    public None eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        scope.setReturnValue(arglist.get(0));
        return new None();
    }

    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            MuaObject.class
    ));
    public int getArgNum() {
        return argtypes.size();
    }
}
