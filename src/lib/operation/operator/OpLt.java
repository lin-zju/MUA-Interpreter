package lib.operation.operator;

import lib.*;
import lib.Number;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class OpLt extends Expr {
    @Override
    public String getOpName() {
        return "lt";
    }

    @Override
    public Word eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        Word x = (Word) arglist.get(0);
        Word y = (Word) arglist.get(1);
        if (x.toNumber() != null && y.toNumber() != null) {
            Number a = x.toNumber();
            Number b = y.toNumber();
            return new Word(a.getValue() < (b.getValue()));
        }
        else {
            Word a = (Word) x;
            Word b = (Word) y;
            return new Word(a.toString().compareTo(b.toString()) < 0);
        }
    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            Word.class,
            Word.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }
}
