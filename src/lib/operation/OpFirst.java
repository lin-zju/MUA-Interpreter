package lib.operation;

import lib.*;
import lib.error.IndexError;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class OpFirst extends Expr {
    @Override
    public String getOpName(){
        return "first";
    }

    @Override
    public MuaObject eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        MuaObject obj = (MuaObject) arglist.get(0);
        if (obj instanceof List) {
            List l = (List)obj;
            if (l.getValue().size() == 0) {
                throw new IndexError("list index out of range");
            }
            return l.getValue().get(0);
        }
        else {
            Word w = (Word)obj;
            if (w.getValue().length() == 0) {
                throw new IndexError("word index out of range");
            }
            return new Word(w.getValue().substring(0, 1));
        }

    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            MuaObject.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }
}
