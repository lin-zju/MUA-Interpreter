package lib.operation;
import lib.*;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Stop extends Expr {
    @Override
    public String getOpName() {
        return "stop";
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
        scope.setStopFlag(true);
//        System.out.println("What");
        return new None();
    }

    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
    ));
    public int getArgNum() {
        return argtypes.size();
    }

}
