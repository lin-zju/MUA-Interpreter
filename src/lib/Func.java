package lib;

import java.util.ArrayList;
import java.util.Arrays;

public class Func extends Expr {
    public Func(ArrayList<MUAObject> arglist) {
        super(SubType.FUNC, arglist, argtypes);
    }

    @Override
    public None eval(Scope scope) throws Exception {
        System.out.println("function not implemented");
        return new None();
    }


    final static private ArrayList<MUAObject.Type> argtypes = new ArrayList<MUAObject.Type>(Arrays.asList(
    ));

    public static int getArgNum() {
        return argtypes.size();
    }

}
