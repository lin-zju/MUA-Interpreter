package lib;

import lib.util.ArgUtil;

import java.util.Arrays;

import java.util.ArrayList;

public class Erase extends Expr{
    public Erase() {
        super(SubType.ERASE);
    }


    @Override
    public None eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getName(), argtypes, arglist);
        Word word = (Word) arglist.get(0);
        scope.removeName(word);
        return new None();
    }

    final static private ArrayList<MUAObject.Type> argtypes = new ArrayList<MUAObject.Type>(Arrays.asList(
            MUAObject.Type.WORD
    ));

    @Override
    public int getArgNum() {
        return argtypes.size();
    }
}
