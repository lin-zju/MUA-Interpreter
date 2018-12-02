package lib.util;

import lib.Number;
import lib.except.MUAExcept;
import lib.except.ArgError;
import lib.except.SyntaxError;
import lib.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

public class ParserUtil {
    public static MUAObject parseBasicObj(String str) throws Exception {
        if (str.startsWith("\"") && str.length() > 1) {
            return new Word(str.substring(1));
        }
        else if (str.equals("false")) {
            return new Bool(false);
        }
        else if (str.equals("true")) {
            return new Bool(true);
        }
        else if (Character.isDigit(str.charAt(0))) {
            return new Number(Double.parseDouble(str));
        }
        else if (str.startsWith("[") && str.endsWith("]")) {
            ArrayList<MUAObject> content = parseObj(parseToken(str.substring(1, str.length() - 1)));
            return new List(content);
        }
        else {
            return null;
        }
    }

    public static ArrayList<MUAObject> parseObj(ArrayList<String> tokens) throws Exception {
        ArrayList<MUAObject> objlist = new ArrayList<>();
        for (int i = tokens.size() - 1; i >= 0; i--) {
            reduce(tokens.get(i), objlist);
        }
        return objlist;
    }

    public static void reduce(String token, ArrayList<MUAObject> objlist) throws Exception {
        MUAObject obj = parseBasicObj(token);
        Class c = null;
        if (obj != null) {
            objlist.add(0, obj);
        }
        else if (tokenToClass.containsKey(token)){
            c = tokenToClass.get(token);
        }
        else {
            c = Func.class;
        }
        if (c != null) {
            int argNum = (int) c.getMethod("getArgNum").invoke(null);
            ArrayList<MUAObject> arglist = new ArrayList<>();
            for (int i = 0; i < argNum; i++) {
                if (!objlist.isEmpty()) {
                    arglist.add(0, objlist.remove(0));
                }
            }
            Constructor ctor = c.getConstructor(ArrayList.class);
            objlist.add(0, (MUAObject) ctor.newInstance(arglist));
        }

    }


    public static ArrayList<String> parseToken(String str) throws SyntaxError {
        ArrayList<String> tokens = new ArrayList<>();
        String[] items = str.tt rim().split("[ \t\n\r]");
        int count = 0;
        ArrayList<String> temp = new ArrayList<>();
        for (String item: items) {
            if (!item.startsWith("[") && !item.endsWith("]") && !item.startsWith(":")) {
                if (count == 0)
                    tokens.add(item);
                else
                    temp.add(item);
            }
            else if (item.startsWith(":")) {
                tokens.add("thing");
                if (item.length() > 1)
                    tokens.add("\"" + item.substring(1));
            }
            else
            {
                if (item.startsWith("["))
                    count++;
                if (item.endsWith("]"))
                    count--;
                temp.add(item);
                if (count < 0) {
                    throw new SyntaxError("Unpaired ']'");
                }
                else if (count == 0) {
                    tokens.add(String.join(" ", temp));
                    temp.clear();
                }
            }
        }
        if (count != 0) {
            throw new SyntaxError("Unpaired [");
        }
        return tokens;

    }


    private static final HashMap<String, Class> tokenToClass = new HashMap<>();
    static {
        tokenToClass.put("make", Make.class);
        tokenToClass.put("erase", Erase.class);
        tokenToClass.put("print", Print.class);
        tokenToClass.put("readlist", Readlist.class);
        tokenToClass.put(":", Thing.class);
        tokenToClass.put("thing", Thing.class);
        tokenToClass.put("isname", Isname.class);
        tokenToClass.put("read", Read.class);
    }
}
