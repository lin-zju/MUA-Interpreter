package lib;

import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Isname extends Expr {
    public Isname() {
        super(SubType.ISNAME);
    }

    @Override
    public Bool eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getName(), argtypes, arglist);
        Word obj = (Word)arglist.get(0);
        return new Bool(scope.hasName(obj));
    }

    final static private ArrayList<MUAObject.Type> argtypes = new ArrayList<MUAObject.Type>(Arrays.asList(
            MUAObject.Type.WORD
    ));
    public int getArgNum() {
        return argtypes.size();
    }
}
