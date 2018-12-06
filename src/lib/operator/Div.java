package lib.operator;

import lib.Expr;
import lib.MUAObject;
import lib.None;
import lib.Scope;
import lib.error.ArithmeticError;
import lib.util.ArgUtil;
import lib.Number;

import java.util.ArrayList;
import java.util.Arrays;

public class Div extends Expr {
    public Div() {
        super(SubType.DIV);
    }

    @Override
    public Number eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getName(), argtypes, arglist);
        Number a = (Number) arglist.get(0);
        Number b = (Number) arglist.get(1);
        if (b.getValue() < 1e-7)
            throw new ArithmeticError("Divide by zero");
        return new Number(a.getValue() / b.getValue());
    }


    final static private ArrayList<MUAObject.Type> argtypes = new ArrayList<MUAObject.Type>(Arrays.asList(
            MUAObject.Type.NUMBER,
            MUAObject.Type.NUMBER
    ));

    public int getArgNum() {
        return argtypes.size();
    }
}
