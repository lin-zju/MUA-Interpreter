package lib.operation;

import lib.Expr;
import lib.MUAObject;
import lib.Number;
import lib.Scope;
import lib.Word;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Int extends Expr {
    @Override
    public String getOpName() {
        return "int";
    }

    @Override
    public Word eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        MUAObject obj = (MUAObject) arglist.get(0);
        int i = (int)(Math.floor(((Number)obj).getValue()));
        return new Word(i);
    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            Number.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }
}
