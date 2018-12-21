package lib.util;

import lib.*;
import lib.error.MUAError;
import lib.error.SyntaxError;
import lib.operation.*;
import lib.operation.operator.*;

import static lib.util.ParserUtil.parseObj;
import static lib.util.ParserUtil.parseToken;

import java.util.ArrayList;
import java.util.Scanner;

public class Interpreter {

    public final static String pOne = ">>> ";
    public final static String pTwo = "... ";
    public Interpreter() {
        this.global = new Scope();
        // put operations into global scope
        global.addName(new Word("make"), new Make());
        global.addName(new Word("erase"), new Erase());
        global.addName(new Word("print"), new Print());
        global.addName(new Word("readlist"), new Readlist());
        global.addName(new Word(":"), new Thing());
        global.addName(new Word("thing"), new Thing());
        global.addName(new Word("isname"), new Isname());
        global.addName(new Word("read"), new Read());
        global.addName(new Word("add"), new Add());
        global.addName(new Word("sub"), new Sub());
        global.addName(new Word("mul"), new Mul());
        global.addName(new Word("div"), new Div());
        global.addName(new Word("mod"), new Mod());
        global.addName(new Word("eq"), new Eq());
        global.addName(new Word("gt"), new Gt());
        global.addName(new Word("lt"), new Lt());
        global.addName(new Word("and"), new And());
        global.addName(new Word("or"), new Or());
        global.addName(new Word("not"), new Not());
        global.addName(new Word("repeat"), new Repeat());
        global.addName(new Word("output"), new Output());
        global.addName(new Word("stop"), new Stop());
        global.addName(new Word("export"), new Export());
        global.addName(new Word("isnumber"), new Isnumber());
        global.addName(new Word("isword"), new Isword());
        global.addName(new Word("islist"), new Islist());
        global.addName(new Word("isbool"), new Isbool());
        global.addName(new Word("isempty"), new Isempty());
        global.addName(new Word("random"), new Random());
        global.addName(new Word("sqrt"), new Sqrt());
        global.addName(new Word("int"), new Int());

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
                else if (Character.isWhitespace(line.charAt(i))
                    || (count > 0 && line.charAt(i) == ']'))
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
        ArrayList<MUAObject> objlist = parseObj(tokens, global);
        if (objlist.size() != 1) {
            throw new SyntaxError("Invalid syntax: more than one object per line");
        }
        else {
            MUAObject obj = objlist.get(0);
            if (obj instanceof Expr) {
                MUAObject ret = ((Expr)obj).eval(global);
                if (! (ret instanceof None)) {
                    System.out.println(ret);
                }
            }
            else {
                System.out.println(obj);
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
