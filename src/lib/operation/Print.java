package lib.operation;

import lib.*;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Print extends Expr {

    public Print() {
        super(SubType.PRINT);
    }

    @Override
    public None eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getName(), argtypes, arglist);
        MUAObject obj = arglist.get(0);
        if (obj instanceof Word)
            System.out.println(obj.getValue());
        else
            System.out.println(obj);
        return new None();

    }

    final static private ArrayList<MUAObject.Type> argtypes = new ArrayList<MUAObject.Type>(Arrays.asList(
            MUAObject.Type.ANY
    ));
    public int getArgNum() {
        return argtypes.size();
    }
}
