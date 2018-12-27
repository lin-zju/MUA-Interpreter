package lib.operation;

import lib.*;
import lib.Number;
import lib.util.*;

import java.util.ArrayList;
import java.util.Arrays;
import static lib.util.RunUtil.runList;

public class OpRepeat extends Expr {
    @Override
    public String getOpName() {
        return "repeat";
    }

    @Override
    public None eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        Number n = (Number) arglist.get(0);
        List l = (List) arglist.get(1);
        for (int i = 0; i < n.getValue(); i++) {
            runList(scope, l);
        }
        return new None();
    }

    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            Number.class,
            List.class
    ));

    public int getArgNum() {
        return argtypes.size();

    }
}
