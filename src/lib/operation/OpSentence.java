package lib.operation;

import lib.*;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class OpSentence extends Expr {
    @Override
    public String getOpName() {
        return "sentence";
    }

    @Override
    public List eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        ArrayList<MuaObject> l = new ArrayList<>();
        for (int i = 0; i < getArgNum(); i++) {
            MuaObject o = arglist.get(i);
            if (o instanceof List) {
                l.addAll(((List)o).getValue());
            }
            else {
                l.add(o);
            }

        }

        return new List(l);

    }

    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            MuaObject.class,
            MuaObject.class
    ));
    public int getArgNum() {
        return argtypes.size();
    }

}
