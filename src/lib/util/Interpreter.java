package lib.util;

import lib.Expr;
import lib.MUAObject;
import lib.Scope;
import lib.error.MUAError;
import lib.error.SyntaxError;

import static lib.util.ParserUtil.parseObj;
import static lib.util.ParserUtil.parseToken;

import java.util.ArrayList;
import java.util.Scanner;

public class Interpreter {

    public final static String pOne = ">>> ";
    public final static String pTwo = "... ";
    public Interpreter() {
        this.global = new Scope();
        System.out.println("[MUA Interpreter | Zhixuan Lin]");
        System.out.println("[Welcome, and enjoy.]");
    }
    public static String getLine(boolean verbose) throws Exception {
        Scanner input = new Scanner(System.in);
        promptOne(verbose);
        String line = getLineWithoutComment();
        while (line.trim().equals("")) {
            promptOne(verbose);
            line = getLineWithoutComment();
        }
        while (true) {
            boolean inWord = false;
            int count = 0;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '"')
                    inWord = true;
                else if (Character.isWhitespace(line.charAt(i)))
                    inWord = false;
                if (line.charAt(i) == '[' && !inWord) {
                    count++;
                }
                else if (line.charAt(i) == ']' && !inWord) {
                    count--;
                }
                if (count < 0) {
                    throw new SyntaxError("Unpaired ']'");
                }
            }
            if (count != 0) {
                promptTwo(verbose);
                String temp = getLineWithoutComment();
                line += " " + temp;
                continue;
            }
            else
                break;
        }
        return line;

    }

    public void evalLine(String line) throws Exception {
        ArrayList<String> tokens = parseToken(line);
        ArrayList<MUAObject> objlist = parseObj(tokens);
        if (objlist.size() != 1) {
            throw new SyntaxError("Invalid syntax: more than one object per line");
        }
        else {
            MUAObject obj = objlist.get(0);
            if (obj.getType() != MUAObject.Type.EXPR) {
                System.out.println(obj);
            }
            else {
                MUAObject ret = ((Expr)obj).eval(global);
                if (ret.getType() != MUAObject.Type.NONE) {
                    System.out.println(ret);
                }
            }
        }
    }
    private static  String getLineWithoutComment() {
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        int i = line.indexOf("//");
        if (i != -1) {
            line = line.substring(0, i);
        }
        return line;
    }

    public void next()  {
        try {
            String line = getLine(true);
            evalLine(line);
        }
        catch (MUAError e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void promptOne(boolean verbose) {
        if (verbose)
            System.out.print(pOne);
    }
    public static void promptTwo(boolean verbose) {
        if (verbose)
            System.out.print(pTwo);
    }


    private Scope global = new Scope();
}
