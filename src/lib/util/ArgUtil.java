package lib.util;
import lib.Expr;
import lib.MUAObject;
import lib.Scope;
import lib.error.ArgError;

import java.util.ArrayList;

public class ArgUtil {
    public static void argCheck(String name,
                                ArrayList<MUAObject.Type> typelist,
                                ArrayList<MUAObject> arglist) throws ArgError {

        // arg count
        if (arglist.size() != typelist.size()) {
            throw new ArgError(name + " takes " + typelist.size() + " arguments but "
                    + arglist.size() + " were given.");
        }
        for (int i = 0; i < arglist.size(); i++) {
            if (arglist.get(i).getType() != typelist.get(i) && typelist.get(i) != MUAObject.Type.ANY) {
                throw new ArgError(name + " expect " + (i + 1) +
                        "th argument to be type " + typelist.get(i).toString()
                + " but " + arglist.get(i).getType().toString() + " were given.");
            }
        }
    }
    public static void evalAll(ArrayList<MUAObject> arglist, Scope scope) throws Exception {
        for (int i = 0; i < arglist.size(); i++) {
            if (arglist.get(i).getType() == MUAObject.Type.EXPR) {
                arglist.set(i, ((Expr)arglist.get(i)).eval(scope));
            }
        }
    }
}
