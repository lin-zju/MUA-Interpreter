package lib.operation;

import lib.*;
import lib.error.SyntaxError;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class OpMake extends Expr {


    @Override
    public String getOpName() {
        return "make";
    }

    @Override
    public None eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        Word word = (Word) arglist.get(0);
        if (!Character.isLetter(word.getValue().charAt(0)))
            throw new SyntaxError("<word> in make must start with a letter");
        MuaObject value = arglist.get(1);
        scope.addName(word, value);

        return new None();
    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            Word.class,
            MuaObject.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }
}
