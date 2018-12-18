package lib.operation;

import lib.Expr;
import lib.MUAObject;
import lib.Scope;
import lib.Word;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Thing extends Expr {
    @Override
    public String getOpName() {
        return "thing";
    }

    @Override
    public MUAObject eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        Word word = (Word) arglist.get(0);
        return scope.getName(word);

    }

    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            Word.class
    ));
    public int getArgNum() {
        return argtypes.size();
    }
}
