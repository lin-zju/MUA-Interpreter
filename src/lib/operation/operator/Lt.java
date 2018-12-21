package lib.operation.operator;

import lib.*;
import lib.Number;
import lib.Bool;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Lt extends Expr {
    @Override
    public String getOpName() {
        return "lt";
    }

    @Override
    public Word eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        MUAObject x = arglist.get(0);
        MUAObject y = arglist.get(1);
        if (x instanceof Number && y instanceof Number) {
            Number a = (Number) x;
            Number b = (Number) y;
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
