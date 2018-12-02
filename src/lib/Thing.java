package lib;

import lib.except.MUAExcept;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Thing extends Expr {
    public Thing(ArrayList<MUAObject> arglist) {
        super(SubType.THING, arglist, argtypes);
    }

    @Override
    public MUAObject eval(Scope scope) throws Exception {
        super.eval(scope);
        Word word = (Word) arglist.get(0);
        return scope.getName(word);

    }

    final static private ArrayList<MUAObject.Type> argtypes = new ArrayList<MUAObject.Type>(Arrays.asList(
            MUAObject.Type.WORD
    ));
    public static int getArgNum() {
        return argtypes.size();
    }
}
