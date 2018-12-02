package lib;

import java.util.ArrayList;
import java.util.Arrays;

public class Print extends Expr {

    public Print(ArrayList<MUAObject> arglist) {
        super(SubType.PRINT, arglist, argtypes);
    }

    @Override
    public None eval(Scope scope) throws Exception {
        super.eval(scope);
        MUAObject obj = arglist.get(0);
        System.out.println(obj);
        return new None();

    }

    final static private ArrayList<MUAObject.Type> argtypes = new ArrayList<MUAObject.Type>(Arrays.asList(
            MUAObject.Type.ANY
    ));
    public static int getArgNum() {
        return argtypes.size();
    }
}
