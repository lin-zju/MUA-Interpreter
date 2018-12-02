package lib;

import java.util.ArrayList;
import java.util.Arrays;

public class Isname extends Expr {
    public Isname(ArrayList<MUAObject> arglist) {
        super(SubType.ISNAME, arglist, argtypes);
    }

    @Override
    public Bool eval(Scope scope) throws Exception {
        super.eval(scope);
        Word obj = (Word)arglist.get(0);
        return new Bool(scope.hasName(obj));


    }

    final static private ArrayList<MUAObject.Type> argtypes = new ArrayList<MUAObject.Type>(Arrays.asList(
            MUAObject.Type.WORD
    ));
    public static int getArgNum() {
        return argtypes.size();
    }
}
