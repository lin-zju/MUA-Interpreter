package lib.operator;

import lib.Bool;
import lib.Expr;
import lib.Scope;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Not extends Expr {
    public Not() {
        super(SubType.ADD);
    }

    @Override
    public Bool eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getName(), argtypes, arglist);
        Bool a = (Bool) arglist.get(0);
        return new Bool(!a.getValue());
    }


    final static private ArrayList<Type> argtypes = new ArrayList<Type>(Arrays.asList(
            Type.BOOL
    ));

    public int getArgNum() {
        return argtypes.size();
    }
}
