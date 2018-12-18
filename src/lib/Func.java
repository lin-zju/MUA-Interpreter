package lib;

import lib.error.SyntaxError;

import java.util.ArrayList;
import java.util.Arrays;

public class Func extends Expr {
    public Func() {
    }

    @Override
    public None eval(Scope scope) throws Exception {
        throw new SyntaxError("function not yet implemented");
//        return new None();
    }


    @Override
    public String getOpName() {
        return "func";
    }


    @Override
    public int getArgNum() {
        return argtypes.size();
    }
    final static private ArrayList<Class> argtypes = new ArrayList<>(Arrays.asList(
    ));
}
