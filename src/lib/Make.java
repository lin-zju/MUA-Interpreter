package lib;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Make extends Expr {

    public Make() {
        super(SubType.MAKE);
    }

    @Override
    public None eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getName(), argtypes, arglist);
        Word word = (Word) arglist.get(0);
        MUAObject value = arglist.get(1);
        scope.addName(word, value);
        return new None();
    }


    final static private ArrayList<MUAObject.Type> argtypes = new ArrayList<MUAObject.Type>(Arrays.asList(
            MUAObject.Type.WORD,
            MUAObject.Type.ANY
    ));

    public int getArgNum() {
        return argtypes.size();
    }





}
