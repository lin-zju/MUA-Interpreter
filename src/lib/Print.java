package lib;

import lib.except.MUAExcept;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Print extends Statement {

    public Print(ArrayList<MUAObject> arglist) {
        super(Type.PRINT, arglist);
    }

    @Override
    public void exec(Scope scope) throws MUAExcept {
        ArgUtil.argCheck(getName(), typelist, arglist);
        MUAObject obj = arglist.get(0);
        System.out.println(obj);


    }

    private ArrayList<MUAObject.Type> typelist = new ArrayList<MUAObject.Type>(Arrays.asList(
            MUAObject.Type.ANY
    ));
}
