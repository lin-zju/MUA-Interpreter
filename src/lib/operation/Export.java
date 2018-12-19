package lib.operation;

import lib.*;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;


public class Export extends Expr {

    @Override
    public String getOpName() {
        return "export";
    }

    @Override
    public None eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        Word word = (Word) arglist.get(0);
        scope.getEnclosingScope().addName(word, scope.getName(word));
        return new None();
    }

    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            Word.class
    ));
    public int getArgNum() {
        return argtypes.size();
    }
}
