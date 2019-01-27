package lib.operation;

import lib.*;
import lib.error.IndexError;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class OpButLast extends Expr {

    @Override
    public String getOpName(){
        return "butlast";
    }

    @Override
    public MuaObject eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        MuaObject obj = (MuaObject) arglist.get(0);
        if (obj instanceof List) {
            List l = (List)obj;
            ArrayList<MuaObject> list = (ArrayList<MuaObject>) l.getValue().clone();
            if (list.size() == 0) {
//                throw new IndexError("list index out of range");
                return new List(list);
            }
            list.remove(list.size() - 1);

            return new List(list);
        }
        else {
            Word w = (Word)obj;
            String str = w.getValue();
            if (w.getValue().length() == 0) {
//                throw new IndexError("word index out of range");
                return new Word(str);
            }
            return new Word(str.substring(0, str.length() - 1));
        }

    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            MuaObject.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }

}
