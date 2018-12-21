package lib.operation;

import lib.*;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Isempty extends Expr {
    @Override
    public String getOpName() {
        return "isempty";
    }

    @Override
    public Word eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        MUAObject obj = arglist.get(0);
        if (obj instanceof Word) {
            return new Word(((Word)obj).getValue().equals(""));
        }
        else
            return new Word(((List) obj).getValue().size() == 0);
    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            MUAObject.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }


}

