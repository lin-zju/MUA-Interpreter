package lib.operation;

import lib.*;
import lib.Number;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Sqrt extends Expr {
    @Override
    public String getOpName() {
        return "sqrt";
    }

    @Override
    public Word eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        MUAObject obj = (MUAObject) arglist.get(0);
        double d = Math.sqrt(((Number) obj).getValue());
        return new Word(d);
    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            Number.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }
}
