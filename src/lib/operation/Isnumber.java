package lib.operation;

import lib.*;
import lib.error.SyntaxError;
import lib.util.ArgUtil;

import lib.Number;
import java.util.ArrayList;
import java.util.Arrays;

public class Isnumber extends Expr {
    @Override
    public String getOpName() {
        return "isnumber";
    }

    @Override
    public Word eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        MUAObject obj = (MUAObject) arglist.get(0);
        if (ArgUtil.typeCast(Number.class, obj) == null) {
            return new Word(false);
        }
        else
            return new Word(true);
    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            MUAObject.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }
}
