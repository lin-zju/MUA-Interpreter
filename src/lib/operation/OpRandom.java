package lib.operation;

import lib.*;
import lib.util.ArgUtil;

import lib.Number;
import java.util.ArrayList;
import java.util.Arrays;

public class OpRandom extends Expr {
    @Override
    public String getOpName() {
        return "random";
    }

    @Override
    public Word eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        MuaObject obj = (MuaObject) arglist.get(0);
        int bound = (int) Math.floor(((Number)obj).getValue());
        java.util.Random r = new java.util.Random();
        return new Word(r.nextInt(bound));
    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            Number.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }
}
