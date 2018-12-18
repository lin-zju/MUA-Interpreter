package lib.operation;

import lib.Expr;
import lib.MUAObject;
import lib.Scope;
import lib.Word;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Thing extends Expr {
    public Thing() {
        super(SubType.THING);
    }

    @Override
    public MUAObject eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getName(), argtypes, arglist);
        Word word = (Word) arglist.get(0);
        return scope.getName(word);

    }

    final static private ArrayList<MUAObject.Type> argtypes = new ArrayList<MUAObject.Type>(Arrays.asList(
            MUAObject.Type.WORD
    ));
    public int getArgNum() {
        return argtypes.size();
    }
}
