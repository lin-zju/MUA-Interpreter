package lib.operation.operator;

import lib.Expr;
import lib.MUAObject;
import lib.Scope;
import lib.util.ArgUtil;
import lib.Number;

import java.util.ArrayList;
import java.util.Arrays;

public class Mul extends Expr {
    public Mul() {
        super(SubType.MUL);
    }

    @Override
    public Number eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getName(), argtypes, arglist);
        Number a = (Number) arglist.get(0);
        Number b = (Number) arglist.get(1);
        return new Number(a.getValue() * b.getValue());
    }


    final static private ArrayList<MUAObject.Type> argtypes = new ArrayList<MUAObject.Type>(Arrays.asList(
            MUAObject.Type.NUMBER,
            MUAObject.Type.NUMBER
    ));

    public int getArgNum() {
        return argtypes.size();
    }
}
