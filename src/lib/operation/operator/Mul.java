package lib.operation.operator;

import lib.*;
import lib.Number;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Mul extends Expr {

    @Override
    public String getOpName() {
        return "mul";
    }

    @Override
    public Word eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        Number a = (Number) arglist.get(0);
        Number b = (Number) arglist.get(1);
        return new Word(a.getValue() * b.getValue());
    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            Number.class,
            Number.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }
}
