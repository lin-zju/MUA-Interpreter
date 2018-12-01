package lib;

import lib.except.MUAExcept;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Thing extends Expression {
    public Thing(ArrayList<MUAObject> arglist) {
        super(Type.THING, arglist);
    }

    @Override
    public MUAObject eval(Scope scope) throws MUAExcept {
        ArgUtil.argCheck(getName(), typelist, arglist);
        Word word = (Word) arglist.get(0);
        return scope.getName(word);

    }

    private ArrayList<MUAObject.Type> typelist = new ArrayList<MUAObject.Type>(Arrays.asList(
            MUAObject.Type.WORD
    ));
}
