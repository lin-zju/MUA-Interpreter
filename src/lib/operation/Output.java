package lib.operation;

import lib.Expr;
import lib.MUAObject;
import lib.None;
import lib.Scope;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Output extends Expr {
    @Override
    public String getOpName() {
        return "output";
    }

    @Override
    public None eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
//        MUAObject obj = arglist.get(0);
//        if (obj instanceof Word)
//            System.out.println(obj);
//        else
//        System.out.println(obj);
        scope.setReturnValue(arglist.get(0));
        return new None();
    }

    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            MUAObject.class
    ));
    public int getArgNum() {
        return argtypes.size();
    }
}
