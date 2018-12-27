package lib.util;

import lib.*;
import lib.Number;
import lib.error.ArgError;
import lib.error.TypeError;

import java.util.ArrayList;
import java.util.HashMap;

public class ArgUtil {
    public static HashMap<Class, String> CLASS_TO_STR = new HashMap<>();
    static {
        CLASS_TO_STR.put(Word.class, "word");
        CLASS_TO_STR.put(Bool.class, "bool");
        CLASS_TO_STR.put(Number.class, "number");
        CLASS_TO_STR.put(List.class, "list");
    }


    public static void argCheck(String name,
                                ArrayList<Class> typelist,
                                ArrayList<MuaObject> arglist) throws ArgError, TypeError {

        // arg count
        if (arglist.size() != typelist.size()) {
            throw new ArgError(name + " takes " + typelist.size() + " arguments but "
                    + arglist.size() + " were given.");
        }


        for (int i = 0; i < arglist.size(); i++) {
            MuaObject o = typeCast(typelist.get(i), arglist.get(i));
            if (o != null) {
                arglist.set(i, o);
            }
            if (!(typelist.get(i).isInstance(arglist.get(i)))) {
                throw new TypeError(name + " expect " + (i + 1) +
                        "th argument to be type '" + CLASS_TO_STR.get(typelist.get(i))
                        + "' but '" + arglist.get(i).getTypeString().toString() + "' were given.");
            }
        }
    }

    public static MuaObject typeCast(Class c, MuaObject o) {
        if (o instanceof Word) {
            if (c == Number.class) {
                return ((Word) o).toNumber();
            }
            else if (c == Bool.class) {
                return ((Word) o).toBool();
            }
        }

        if (c == Word.class) {
            if (o instanceof Number) {
                return new Word(o.toString());
            }
            else if (o instanceof Bool) {
                return new Word(o.toString());
            }
        }
        return null;
    }

//
//    public static int argCheck(String name,
//                                ArrayList<MuaObject.Type>[] typelists,
//                                ArrayList<MuaObject> arglist) throws ArgError, TypeError {
//
//        // arg count
//        if (arglist.size() != typelists[0].size()) {
//            throw new ArgError(name + " takes " + typelists[0].size() + " arguments but "
//                    + arglist.size() + " were given.");
//        }
//
//        boolean match = false;
//        int i = 0;
//        for (i = 0; i < typelists.length; i++) {
//            ArrayList<MuaObject.Type> typelist = typelists[i];
//            boolean pass = true;
//            for (int j = 0; j < arglist.size(); j++) {
//                if (arglist.get(j).getType() != typelist.get(j) && typelist.get(j) != MuaObject.Type.ANY) {
//                    pass = false;
//                }
//            }
//            if (pass) {
//                match = true;
//                break;
//            }
//
//        }
//        if (!match) {
//            throw new TypeError("argument types does not match any signature of '" +
//                    name + "'");
//        }
//        return i;
//    }

//    public static void evalAll(ArrayList<MuaObject> arglist, Scope scope) throws Exception {
//        for (int i = 0; i < arglist.size(); i++) {
//
//            if (arglist.get(i) instanceof Expr) {
//                arglist.set(i, ((Expr) arglist.get(i)).eval(scope, ));
//            }
//        }
//    }
}
