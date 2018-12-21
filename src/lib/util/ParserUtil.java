package lib.util;

import lib.Number;
import lib.error.SyntaxError;
import lib.*;
import lib.operation.*;
import lib.operation.operator.*;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ParserUtil {
    public static MUAObject parseBasicObj(String str) throws Exception {
        if (str.startsWith("\"")) {
//            if (str.length() == 1)
//                throw new SyntaxError("empty word body");
            return new Word(str.substring(1));
        }
        else if (str.equals("false")) {
//            return new Bool(false);
            return new Word(str);
        }
        else if (str.equals("true")) {
//            return new Bool(true);
            return new Word(str);
        }

        else if (Character.isDigit(str.charAt(0)) || str.charAt(0) == '-') {
            try {
                Number test =  new Number(Double.parseDouble(str));
                return new Word(str);
            }
            catch (NumberFormatException e){
                throw new SyntaxError("invalid number literal: '" + str + "'");

            }
        }
        else if (str.startsWith("[") && str.endsWith("]")) {
            ArrayList<String> content = parseToken(str.substring(1, str.length() - 1));
            ArrayList<MUAObject> objlist = new ArrayList<>();
            for (String token : content) {
                if (token.startsWith("[") && token.endsWith("]")) {
                    objlist.add(parseBasicObj(token));
                }
                else {
                    objlist.add(new Word(token));
                }

            }
            return new List(objlist);
        }
        else {
            return null;
        }
    }

    public static ArrayList<MUAObject> parseObj(ArrayList<String> tokens, Scope scope) throws Exception {
        ArrayList<MUAObject> objlist = new ArrayList<>();
        for (int i = tokens.size() - 1; i >= 0; i--) {
            reduce(tokens.get(i), objlist, scope);
        }
        return objlist;
    }


    public static void reduce(String token, ArrayList<MUAObject> objlist, Scope scope) throws Exception {
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
            Func func = new Func(token, scope);
            int argNum = func.getArgNum();
            ArrayList<MUAObject> arglist = new ArrayList<>();
            for (int i = 0; i < argNum; i++) {
                if (!objlist.isEmpty()) {
                    arglist.add(objlist.remove(0));
                }
            }
            func.setArglist(arglist);
            objlist.add(0, func);
        }
    }


    public static ArrayList<String> parseToken(String str) throws SyntaxError {
        ArrayList<String> tokens = new ArrayList<>();
        if (str.trim().equals(""))
            return tokens;
        String[] itemStr = str.trim().split("\\s+");
        ArrayList<String> items = new ArrayList<>(Arrays.asList(itemStr));
        int count = 0;
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            String item = items.get(i);
            if (!item.startsWith("[") && !item.endsWith("]") && !item.startsWith(":")) {
                if (count == 0)
                    tokens.add(item);
                else
                    temp.add(item);
            }
            else if (item.startsWith(":")) {
                if (count == 0)
                    tokens.add("thing");
                else
                    temp.add("thing");
                if (item.length() > 1) {
                    items.set(i, "\"" + item.substring(1));
                    i--;
                }
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
        keywordToClass.put("repeat", Repeat.class);
        keywordToClass.put("output", Output.class);
        keywordToClass.put("stop", Stop.class);
        keywordToClass.put("export", Export.class);
        keywordToClass.put("isnumber", Isnumber.class);
        keywordToClass.put("isword", Isword.class);
        keywordToClass.put("islist", Islist.class);
        keywordToClass.put("isbool", Isbool.class);
        keywordToClass.put("isempty", Isempty.class);
        keywordToClass.put("random", Random.class);
        keywordToClass.put("sqrt", Sqrt.class);
        keywordToClass.put("int", Int.class);

    }
}
