package lib.util;

import lib.Number;
import lib.error.SyntaxError;
import lib.*;
import lib.operation.*;
import lib.operation.operator.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;

public class ParserUtil {
    public static MUAObject parseBasicObj(String str) throws Exception {
        if (str.startsWith("\"")) {
            if (str.length() == 1)
                throw new SyntaxError("empty word body");
            return new Word(str.substring(1));
        }
        else if (str.equals("false")) {
            return new Bool(false);
        }
        else if (str.equals("true")) {
            return new Bool(true);
        }
        else if (Character.isDigit(str.charAt(0)) || str.charAt(0) == '-') {
            try {
                return new Number(Double.parseDouble(str));
            }
            catch (NumberFormatException e){
                throw new SyntaxError("invalid number literal: '" + str + "'");

            }
        }
        else if (str.startsWith("[") && str.endsWith("]")) {
            ArrayList<String> content = parseToken(str.substring(1, str.length() - 1));
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
        else if (keywordToClass.containsKey(token)){
            c = keywordToClass.get(token);
            Constructor ctor = c.getConstructor();
            Expr expr = (Expr) ctor.newInstance();
            int argNum = (int) c.getMethod("getArgNum").invoke(expr);
            ArrayList<MUAObject> arglist = new ArrayList<>();
            for (int i = 0; i < argNum; i++) {
                if (!objlist.isEmpty()) {
                    arglist.add(objlist.remove(0));
                }
            }
            expr.setArglist(arglist);
            objlist.add(0, expr);
        }
        else {
            c = Func.class;
            objlist.add(new Func());
        }
    }


    public static ArrayList<String> parseToken(String str) throws SyntaxError {
        ArrayList<String> tokens = new ArrayList<>();
        if (str.trim().equals(""))
            return tokens;
        String[] items = str.trim().split("\\s+");
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
                String prefix = "[";
                while (item.startsWith(prefix)) {
                    count++;
                    prefix += "[";
                }
                String suffix = "]";
                while (item.endsWith(suffix))
                {
                    count--;
                    suffix += "]";
                }
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


    private static final HashMap<String, Class> keywordToClass = new HashMap<>();
    static {
        keywordToClass.put("make", Make.class);
        keywordToClass.put("erase", Erase.class);
        keywordToClass.put("print", Print.class);
        keywordToClass.put("readlist", Readlist.class);
        keywordToClass.put(":", Thing.class);
        keywordToClass.put("thing", Thing.class);
        keywordToClass.put("isname", Isname.class);
        keywordToClass.put("read", Read.class);
        keywordToClass.put("add", Add.class);
        keywordToClass.put("sub", Sub.class);
        keywordToClass.put("mul", Mul.class);
        keywordToClass.put("div", Div.class);
        keywordToClass.put("mod", Mod.class);
        keywordToClass.put("eq", Eq.class);
        keywordToClass.put("gt", Gt.class);
        keywordToClass.put("lt", Lt.class);
        keywordToClass.put("and", And.class);
        keywordToClass.put("or", Or.class);
        keywordToClass.put("not", Not.class);

    }
}
