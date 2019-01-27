package lib.operation;

import lib.*;
import lib.Number;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class OpPoall extends Expr {
    @Override
    public String getOpName() {
        return "poall";
    }

    @Override
    public None eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        for (String s : scope.getAllName())
            System.out.println(s);
        return new None();
    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
    ));

    public int getArgNum() {
        return argtypes.size();
    }

}
