package lib;
import lib.except.ArgError;
import java.util.ArrayList;
import java.util.Arrays;

import lib.except.MUAExcept;
import lib.util.ArgUtil;

public class Make extends Expr {

    public Make(ArrayList<MUAObject> arglist) {
        super(SubType.MAKE, arglist, argtypes);
    }

    @Override
    public None eval(Scope scope) throws Exception {
        super.eval(scope);
        Word word = (Word) arglist.get(0);
        MUAObject value = arglist.get(1);
        scope.addName(word, value);
        return new None();
    }


    final static private ArrayList<MUAObject.Type> argtypes = new ArrayList<MUAObject.Type>(Arrays.asList(
            MUAObject.Type.WORD,
            MUAObject.Type.ANY
    ));

    public static int getArgNum() {
        return argtypes.size();
    }





}
