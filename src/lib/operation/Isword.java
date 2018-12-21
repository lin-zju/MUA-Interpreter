package lib.operation;

import lib.Expr;
import lib.MUAObject;
import lib.Scope;
import lib.Word;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Isword extends Expr {
    @Override
    public String getOpName() {
        return "isword";
    }

    @Override
    public Word eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        MUAObject obj = (MUAObject) arglist.get(0);
        if (obj instanceof Word) {
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
