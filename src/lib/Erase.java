package lib;

import java.util.Arrays;

import java.util.ArrayList;

public class Erase extends Expr{
    public Erase(ArrayList<MUAObject> arglist) {
        super(SubType.ERASE, arglist, argtypes);
    }


    @Override
    public None eval(Scope scope) throws Exception {
        super.eval(scope);
        Word word = (Word) arglist.get(0);
        scope.removeName(word);
        return new None();
    }

    final static private ArrayList<MUAObject.Type> argtypes = new ArrayList<MUAObject.Type>(Arrays.asList(
            MUAObject.Type.WORD
    ));

    public static int getArgNum() {
        return argtypes.size();
    }
}
