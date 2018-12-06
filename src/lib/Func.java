package lib;

import java.util.ArrayList;
import java.util.Arrays;

public class Func extends Expr {
    public Func() {
        super(SubType.FUNC);
    }

    @Override
    public None eval(Scope scope) throws Exception {
        System.out.println("function not implemented");
        return new None();
    }


    @Override
    public String getName() {
        return "func";
    }

    final static private ArrayList<MUAObject.Type> argtypes = new ArrayList<MUAObject.Type>(Arrays.asList(
    ));

    @Override
    public int getArgNum() {
        return argtypes.size();
    }

}
