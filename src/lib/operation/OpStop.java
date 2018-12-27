package lib.operation;
import lib.*;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class OpStop extends Expr {
    @Override
    public String getOpName() {
        return "stop";
    }

    @Override
    public None eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        if (scope.getEnclosingScope() != null) {
            throw new StopSignal();
        }
        return new None();
    }

    public static class StopSignal extends Exception {

    }

    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
    ));
    public int getArgNum() {
        return argtypes.size();
    }

}
