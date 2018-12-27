package lib.operation;

import lib.*;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class OpIsempty extends Expr {
    @Override
    public String getOpName() {
        return "isempty";
    }

    @Override
    public Word eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        MuaObject obj = arglist.get(0);
        if (obj instanceof Word) {
            return new Word(((Word)obj).getValue().equals(""));
        }
        else
            return new Word(((List) obj).getValue().size() == 0);
    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            MuaObject.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }


}

