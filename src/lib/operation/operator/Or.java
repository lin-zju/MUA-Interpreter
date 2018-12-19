package lib.operation.operator;

import lib.Bool;
import lib.Expr;
import lib.Scope;
import lib.Word;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Or extends Expr {
    @Override
    public String getOpName() {
        return "or";
    }

    @Override
    public Word eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        Bool a = (Bool) arglist.get(0);
        Bool b = (Bool) arglist.get(1);
        return new Word(a.getValue() || b.getValue());
    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            Bool.class,
            Bool.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }
}
