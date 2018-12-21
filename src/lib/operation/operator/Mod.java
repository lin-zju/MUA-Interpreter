package lib.operation.operator;

import lib.*;
import lib.Number;
import lib.error.ArithmeticError;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Mod extends Expr {
    @Override
    public String getOpName() {
        return "gt";
    }

    @Override
    public Word eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        Number a = (Number) arglist.get(0);
        Number b = (Number) arglist.get(1);
        if (b.getValue() < 1e-7)
            throw new ArithmeticError("Divide by zero");
        return new Word(a.getValue() % b.getValue());
    }

    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            Number.class,
            Number.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }
}
