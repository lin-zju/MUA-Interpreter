package lib.operation;

import lib.*;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Islist extends Expr {
    @Override
    public String getOpName() {
        return "islist";
    }

    @Override
    public Word eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        MUAObject obj = (MUAObject) arglist.get(0);
        if (obj instanceof List) {
            return new Word(true);
        }
        else
            return new Word(false);
    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            MUAObject.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }

}
