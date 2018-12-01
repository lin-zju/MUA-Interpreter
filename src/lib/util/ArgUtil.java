package lib.util;
import lib.MUAObject;
import lib.except.ArgError;

import java.util.ArrayList;

public class ArgUtil {
    public static void argCheck(String name,
                                ArrayList<MUAObject.Type> typelist,
                                ArrayList<MUAObject> arglist) throws ArgError {

        // arg count
        if (arglist.size() != typelist.size()) {
            throw new ArgError(name + " takes 2 arguments but "
                    + arglist.size() + " were given.");
        }
        for (int i = 0; i < arglist.size(); i++) {
            if (arglist.get(i).getType() != typelist.get(i)) {
                throw new ArgError(name + " expect " + (i + 1) +
                        "th argument to be type " + typelist.get(i).toString()
                + " but " + arglist.get(i).getType().toString() + " were given.");
            }
        }
    }
}
