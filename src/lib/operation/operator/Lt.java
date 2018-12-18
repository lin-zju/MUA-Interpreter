package lib.operation.operator;

import lib.*;
import lib.Number;
import lib.Bool;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Lt extends Expr {
    public Lt() {
        super(SubType.LT);
    }

    @Override
    public Bool eval(Scope scope) throws Exception {
        super.eval(scope);
        int i = ArgUtil.argCheck(getName(), argtypes, arglist);
        if (i == 0) {
            Number a = (Number) arglist.get(0);
            Number b = (Number) arglist.get(1);
            return new Bool(a.getValue() < (b.getValue()));
        }
        else {
            Word a = (Word) arglist.get(0);
            Word b = (Word) arglist.get(1);
            return new Bool(a.getValue().compareTo(b.getValue()) < 0);
        }
    }


    final static private ArrayList[] argtypes = new ArrayList[]{
            new ArrayList<Type>(Arrays.asList(Type.NUMBER, Type.NUMBER)),
            new ArrayList<Type>(Arrays.asList(Type.WORD, Type.WORD))
    };

    public int getArgNum() {
        return argtypes[0].size();
    }
}
