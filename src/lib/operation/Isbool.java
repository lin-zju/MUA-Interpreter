package lib.operation;

import lib.*;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Isbool extends Expr {
    @Override
    public String getOpName() {
        return "isbool";
    }

    @Override
    public Word eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        MUAObject obj = (MUAObject) arglist.get(0);
        if (ArgUtil.typeCast(Bool.class, obj) == null) {
            return new Word(false);
        } else
            return new Word(true);
    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            MUAObject.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }
}
