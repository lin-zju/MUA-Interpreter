package lib.operation;

import lib.*;
import lib.util.ArgUtil;

import java.util.Arrays;

import java.util.ArrayList;

public class Erase extends Expr {

    @Override
    public String getOpName() {
        return "erase";
    }

    @Override
    public None eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        Word word = (Word) arglist.get(0);
        scope.removeName(word);
        return new None();
    }

    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            Word.class
    ));

    @Override
    public int getArgNum() {
        return argtypes.size();
    }
}
