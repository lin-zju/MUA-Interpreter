package lib;

import lib.except.MUAExcept;
import lib.util.ArgUtil;
import java.util.Arrays;

import java.util.ArrayList;

public class Erase extends Statement {
    public Erase(ArrayList<MUAObject> arglist) {
        super(Type.ERASE, arglist);
    }



    @Override
    public void exec(Scope scope) throws MUAExcept {
        ArgUtil.argCheck(getName(), typelist, arglist);
        Word word = (Word) arglist.get(0);
        scope.removeName(word);
    }

    private ArrayList<MUAObject.Type> typelist = new ArrayList<MUAObject.Type>(Arrays.asList(
            MUAObject.Type.WORD
    ));
}
