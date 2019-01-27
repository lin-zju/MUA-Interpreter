package lib.operation;

import lib.Expr;
import lib.List;
import lib.MuaObject;
import lib.Scope;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class OpJoin extends Expr {
    @Override
    public String getOpName() {
        return "join";
    }

    @Override
    public List eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        List a = (List) arglist.get(0);
        MuaObject b =  arglist.get(1);
        ArrayList <MuaObject> l = (ArrayList<MuaObject>) a.getValue().clone();
        l.add(b);
        return new List(l);

    }

    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            List.class,
            MuaObject.class
    ));
    public int getArgNum() {
        return argtypes.size();
    }
}
