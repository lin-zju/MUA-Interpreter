package lib.operation;

import lib.*;
import lib.util.ArgUtil;
import lib.util.RunUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class OpList extends Expr {
    @Override
    public String getOpName() {
        return "list";
    }

    @Override
    public List eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        MuaObject a =  arglist.get(0);
        MuaObject b =  arglist.get(1);
        ArrayList<MuaObject> list = new ArrayList<>();
        list.add(a);
        list.add(b);

        return new List(list);

    }

    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            MuaObject.class,
            MuaObject.class
    ));
    public int getArgNum() {
        return argtypes.size();
    }
}
