package lib.util;

import lib.*;
import lib.error.MuaError;
import lib.error.SyntaxError;
import lib.operation.*;
import lib.operation.operator.*;

import static lib.util.ParserUtil.evalObj;
import static lib.util.ParserUtil.parseExpr;
import static lib.util.ParserUtil.parseToken;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Interpreter {

    public final static String pOne = ">>> ";
    public final static String pTwo = "... ";
    public Interpreter(boolean verbose) {
        this.global = new Scope();
        // put operations into global scope
        global.addName(new Word("make"), new OpMake());
        global.addName(new Word("erase"), new OpErase());
        global.addName(new Word("print"), new OpPrint());
        global.addName(new Word("readlist"), new OpReadlist());
        global.addName(new Word(":"), new OpThing());
        global.addName(new Word("thing"), new OpThing());
        global.addName(new Word("isname"), new OpIsname());
        global.addName(new Word("read"), new OpRead());
        global.addName(new Word("add"), new OpAdd());
        global.addName(new Word("sub"), new OpSub());
        global.addName(new Word("mul"), new OpMul());
        global.addName(new Word("div"), new OpDiv());
        global.addName(new Word("mod"), new OpMod());
        global.addName(new Word("eq"), new OpEq());
        global.addName(new Word("gt"), new OpGt());
        global.addName(new Word("lt"), new OpLt());
        global.addName(new Word("and"), new OpAnd());
        global.addName(new Word("or"), new OpOr());
        global.addName(new Word("not"), new OpNot());
        global.addName(new Word("repeat"), new OpRepeat());
        global.addName(new Word("output"), new OpOutput());
        global.addName(new Word("stop"), new OpStop());
        global.addName(new Word("export"), new OpExport());
        global.addName(new Word("isnumber"), new OpIsnumber());
        global.addName(new Word("isword"), new OpIsword());
        global.addName(new Word("islist"), new OpIslist());
        global.addName(new Word("isbool"), new OpIsbool());
        global.addName(new Word("isempty"), new OpIsempty());
        global.addName(new Word("random"), new OpRandom());
        global.addName(new Word("sqrt"), new OpSqrt());
        global.addName(new Word("int"), new OpInt());
        global.addName(new Word("word"), new OpWord());
        global.addName(new Word("if"), new OpIf());
        global.addName(new Word("sentence"), new OpSentence());
        global.addName(new Word("list"), new OpList());
        global.addName(new Word("join"), new OpJoin());
        global.addName(new Word("first"), new OpFirst());
        global.addName(new Word("last"), new OpLast());
        global.addName(new Word("butfirst"), new OpButfirst());
        global.addName(new Word("butlast"), new OpButLast());

        if (verbose) {
            System.out.println("[MUA Interpreter | Zhixuan Lin]");
            System.out.println("[Welcome, and enjoy.]");
        }
    }
    public static String getLine(boolean verbose, Scanner input) throws Exception {
        promptOne(verbose);
        String line = getLineWithoutComment(input);
        while (line.trim().equals("")) {
            promptOne(verbose);
            line = getLineWithoutComment(input);
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
                String temp = getLineWithoutComment(input);
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
        ArrayList<ArrayList<String>> exprs = parseExpr(tokens, global);
        if (exprs.size() != 1) {
            throw new SyntaxError("Invalid syntax: more than one expression per line");
        }
        else {
            MuaObject ret = evalObj(exprs.get(0), global);
            if (! (ret instanceof None)) {
                System.out.println(ret);
            }
        }
    }

    public void runFile(String filename) {
        try (
            Scanner input = new Scanner(new FileInputStream(filename))
        ) {
            while (input.hasNext()) {
                String line = getLine(false, input);
                ArrayList<String> tokens = parseToken(line);
                ArrayList<ArrayList<String>> exprs = parseExpr(tokens, global);
                if (exprs.size() != 1) {
                    throw new SyntaxError("Invalid syntax: more than one expression per line");
                }
                else {
                    evalObj(exprs.get(0), global);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Cannot open file " + filename);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    private static  String getLineWithoutComment(Scanner input) {
        String line = input.nextLine();
        int i = line.indexOf("//");
        if (i != -1) {
            line = line.substring(0, i);
        }
        return line;
    }

    public void next(boolean verbose, Scanner in)  {
        try {
            String line = getLine(verbose, in);
            evalLine(line);
        }
        catch (MuaError e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
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
