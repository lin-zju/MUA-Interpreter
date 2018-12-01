package lib;

import lib.except.ArgError;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Expr extends Statement {
    public Expr(ArrayList<MUAObject> arglist) {
        super(Type.EXPR, arglist);
    }

    @Override
    public void exec(Scope scope) throws ArgError {
        ArgUtil.argCheck(getName(), typelist, arglist);
        MUAObject value = arglist.get(0);
        System.out.println(value);

    }

    private ArrayList<MUAObject.Type> typelist = new ArrayList<MUAObject.Type>(Arrays.asList(
            MUAObject.Type.ANY
    ));

}
