package lib.operation;

import lib.Expr;
import lib.MuaObject;
import lib.Scope;
import lib.Word;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class OpWord extends Expr {
    @Override
    public String getOpName() {
        return "word";
    }

    @Override
    public Word eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        Word a = (Word) arglist.get(0);
        Word b = (Word) arglist.get(1);
        return new Word(a.getValue() + b.getValue());

    }

    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            Word.class,
            Word.class
    ));
    public int getArgNum() {
        return argtypes.size();
    }

}
