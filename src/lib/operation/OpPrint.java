package lib.operation;

import lib.*;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class OpPrint extends Expr {

    @Override
    public String getOpName() {
        return "print";
    }

    @Override
    public None eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        MuaObject obj = arglist.get(0);
//        if (obj instanceof Word)
//            System.out.println(obj);
//        else
        System.out.println(obj);
        return new None();

    }

    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            MuaObject.class
    ));
    public int getArgNum() {
        return argtypes.size();
    }
}
