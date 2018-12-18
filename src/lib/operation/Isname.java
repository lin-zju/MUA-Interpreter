package lib.operation;

import lib.*;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Isname extends Expr {

    @Override
    public String getOpName() {
        return "isname";
    }

    @Override
    public Bool eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        Word obj = (Word)arglist.get(0);
        return new Bool(scope.hasName(obj));
    }

    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            Word.class
    ));
    public int getArgNum() {
        return argtypes.size();
    }
}
