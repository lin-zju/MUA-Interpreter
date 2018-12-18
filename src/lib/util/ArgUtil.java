package lib.util;

import lib.Expr;
import lib.MUAObject;
import lib.Scope;
import lib.error.ArgError;
import lib.error.SyntaxError;
import lib.error.TypeError;

import java.util.ArrayList;

public class ArgUtil {
    public static void argCheck(String name,
                                ArrayList<MUAObject.Type> typelist,
                                ArrayList<MUAObject> arglist) throws ArgError, TypeError {

        // arg count
        if (arglist.size() != typelist.size()) {
            throw new ArgError(name + " takes " + typelist.size() + " arguments but "
                    + arglist.size() + " were given.");
        }
        for (int i = 0; i < arglist.size(); i++) {
            if (arglist.get(i).getType() != typelist.get(i) && typelist.get(i) != MUAObject.Type.ANY) {
                throw new TypeError(name + " expect " + (i + 1) +
                        "th argument to be type '" + typelist.get(i).toString()
                        + "' but '" + arglist.get(i).getType().toString() + "' were given.");
            }
        }
    }

    public static int argCheck(String name,
                                ArrayList<MUAObject.Type>[] typelists,
                                ArrayList<MUAObject> arglist) throws ArgError, TypeError {

        // arg count
        if (arglist.size() != typelists[0].size()) {
            throw new ArgError(name + " takes " + typelists[0].size() + " arguments but "
                    + arglist.size() + " were given.");
        }

        boolean match = false;
        int i = 0;
        for (i = 0; i < typelists.length; i++) {
            ArrayList<MUAObject.Type> typelist = typelists[i];
            boolean pass = true;
            for (int j = 0; j < arglist.size(); j++) {
                if (arglist.get(j).getType() != typelist.get(j) && typelist.get(j) != MUAObject.Type.ANY) {
                    pass = false;
                }
            }
            if (pass) {
                match = true;
                break;
            }

        }
        if (!match) {
            throw new TypeError("argument types does not match any signature of '" +
                    name + "'");
        }
        return i;
    }

    public static void evalAll(ArrayList<MUAObject> arglist, Scope scope) throws Exception {
        for (int i = 0; i < arglist.size(); i++) {

            if (arglist.get(i).getType() == MUAObject.Type.EXPR) {
                arglist.set(i, ((Expr) arglist.get(i)).eval(scope));
            }
        }
    }
}
