package lib.util;

import lib.Number;
import lib.error.SyntaxError;
import lib.*;
import lib.operation.*;
import lib.operation.operator.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class ParserUtil {
    public static MuaObject parseBasicObj(String str) throws Exception {
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
            ArrayList<MuaObject> objlist = new ArrayList<>();
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

    public static ArrayList<ArrayList<String>> parseExpr(ArrayList<String> tokens, Scope scope) throws Exception {
        // do evaluation
        Stack<ArrayList<String>> exprStack = new Stack<>();
        for (int i = tokens.size() - 1; i >= 0; i--) {
            reduceExpr(tokens.get(i), exprStack, scope);
        }
        ArrayList<ArrayList<String>> exprList = new ArrayList<>();
        while (!exprStack.isEmpty()) {
            exprList.add(exprStack.pop());
        }

        return exprList;
    }


    public static void reduceExpr(String token, Stack<ArrayList<String>> exprStack, Scope scope) throws Exception {
        MuaObject obj = parseBasicObj(token);
        ArrayList<String> expr = new ArrayList<>();
        if (obj != null) {
            // primitive objects
            expr.add(token);
            exprStack.push(expr);
        }
        else {
            // operation
            MuaObject o = scope.getName(new Word(token));
            Expr op;

            if (o instanceof Expr) {
                op = (Expr) o;
            }
            else {
                op = new Func(token, scope);
            }
            int argNum = op.getArgNum();
            ArrayList<ArrayList<String>> arglist = new ArrayList<>();
            for (int i = 0; i < argNum; i++) {
                if (!exprStack.isEmpty()) {
                    arglist.add(exprStack.pop());
                }
            }
            // construct expr
            expr.add(token);
            for (ArrayList<String> arg : arglist) {
                expr.addAll(arg);
            }
            exprStack.add(expr);
        }
    }
    public static ArrayList<String> parseSingleExpr(Scope scope, ArrayList<String> tokens) throws Exception {
        ArrayList<String> expr = new ArrayList<>();
        if (tokens.isEmpty()) {
            return expr;
        }
        else {
            // pop first token
            String token = tokens.get(0);
            tokens.remove(0);
            expr.add(token);

            MuaObject obj = parseBasicObj(token);
            if (obj == null) {
                // operation
                MuaObject o = scope.getName(new Word(token));
                Expr op;

                if (o instanceof Expr) {
                    op = (Expr) o;
                }
                else {
                    op = new Func(token, scope);
                }
                int argNum = op.getArgNum();
                for (int i = 0; i < argNum; i++) {
                    if (!tokens.isEmpty()) {
                        expr.addAll(parseSingleExpr(scope, tokens));
                    }
                }
                // construct expr
            }
            return expr;

        }

    }

    public static MuaObject evalObj(ArrayList<String> tokens, Scope scope) throws Exception {
        // do evaluation
        Stack<MuaObject> opStack = new Stack<>();
        for (int i = tokens.size() - 1; i >= 0; i--) {
            reduceObj(tokens.get(i), opStack, scope);
        }
        if (opStack.size() != 1) {
            throw new SyntaxError("more than one statement per line");
        }
        return  opStack.pop();
    }


    public static void reduceObj(String token, Stack<MuaObject> opStack, Scope scope) throws Exception {
        MuaObject obj = parseBasicObj(token);
        if (obj != null) {
            // primitive objects
            opStack.push(obj);
        }
        else {
            // operation
            MuaObject o = scope.getName(new Word(token));
            Expr expr;

            if (o instanceof Expr) {
                expr = (Expr) o;
            }
            else {
                expr = new Func(token, scope);
            }
            int argNum = expr.getArgNum();
            ArrayList<MuaObject> arglist = new ArrayList<>();
            for (int i = 0; i < argNum; i++) {
                if (!opStack.isEmpty()) {
                    arglist.add(opStack.pop());
                }
            }
            opStack.push(expr.eval(scope, arglist));
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
                while (item.endsWith(suffix) && count > 0)
                {
                    count--;
                    suffix += "]";
                }
                temp.add(item);
//                if (count > 0) {
//                    throw new SyntaxError("Unpaired '['");
//                }
                if (count == 0) {
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
        keywordToClass.put("make", OpMake.class);
        keywordToClass.put("erase", OpErase.class);
        keywordToClass.put("print", OpPrint.class);
        keywordToClass.put("readlist", OpReadlist.class);
        keywordToClass.put(":", OpThing.class);
        keywordToClass.put("thing", OpThing.class);
        keywordToClass.put("isname", OpIsname.class);
        keywordToClass.put("read", OpRead.class);
        keywordToClass.put("add", OpAdd.class);
        keywordToClass.put("sub", OpSub.class);
        keywordToClass.put("mul", OpMul.class);
        keywordToClass.put("div", OpDiv.class);
        keywordToClass.put("mod", OpMod.class);
        keywordToClass.put("eq", OpEq.class);
        keywordToClass.put("gt", OpGt.class);
        keywordToClass.put("lt", OpLt.class);
        keywordToClass.put("and", OpAnd.class);
        keywordToClass.put("or", OpOr.class);
        keywordToClass.put("not", OpNot.class);
        keywordToClass.put("repeat", OpRepeat.class);
        keywordToClass.put("output", OpOutput.class);
        keywordToClass.put("stop", OpStop.class);
        keywordToClass.put("export", OpExport.class);
        keywordToClass.put("isnumber", OpIsnumber.class);
        keywordToClass.put("isword", OpIsword.class);
        keywordToClass.put("islist", OpIslist.class);
        keywordToClass.put("isbool", OpIsbool.class);
        keywordToClass.put("isempty", OpIsempty.class);
        keywordToClass.put("random", OpRandom.class);
        keywordToClass.put("sqrt", OpSqrt.class);
        keywordToClass.put("int", OpInt.class);

    }
}
