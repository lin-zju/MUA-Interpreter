package lib.operation;

import lib.*;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class OpIslist extends Expr {
    @Override
    public String getOpName() {
        return "islist";
    }

    @Override
    public Word eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        MuaObject obj = (MuaObject) arglist.get(0);
        if (obj instanceof List) {
            return new Word(true);
        }
        else
            return new Word(false);
    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            MuaObject.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }

}
