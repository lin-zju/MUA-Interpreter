package lib.util;

import lib.*;
import lib.Number;
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
        this.builtin = new Scope("builtin", Scope.Type.BUILTIN, null);
        this.global = new Scope("global", Scope.Type.GLOBAL, builtin);
        // put operations into global scope
        builtin.addName(new Word("make"), new OpMake());
        builtin.addName(new Word("erase"), new OpErase());
        builtin.addName(new Word("print"), new OpPrint());
        builtin.addName(new Word("readlist"), new OpReadlist());
        builtin.addName(new Word(":"), new OpThing());
        builtin.addName(new Word("thing"), new OpThing());
        builtin.addName(new Word("isname"), new OpIsname());
        builtin.addName(new Word("read"), new OpRead());
        builtin.addName(new Word("add"), new OpAdd());
        builtin.addName(new Word("sub"), new OpSub());
        builtin.addName(new Word("mul"), new OpMul());
        builtin.addName(new Word("div"), new OpDiv());
        builtin.addName(new Word("mod"), new OpMod());
        builtin.addName(new Word("eq"), new OpEq());
        builtin.addName(new Word("gt"), new OpGt());
        builtin.addName(new Word("lt"), new OpLt());
        builtin.addName(new Word("and"), new OpAnd());
        builtin.addName(new Word("or"), new OpOr());
        builtin.addName(new Word("not"), new OpNot());
        builtin.addName(new Word("repeat"), new OpRepeat());
        builtin.addName(new Word("output"), new OpOutput());
        builtin.addName(new Word("stop"), new OpStop());
        builtin.addName(new Word("export"), new OpExport());
        builtin.addName(new Word("isnumber"), new OpIsnumber());
        builtin.addName(new Word("isword"), new OpIsword());
        builtin.addName(new Word("islist"), new OpIslist());
        builtin.addName(new Word("isbool"), new OpIsbool());
        builtin.addName(new Word("isempty"), new OpIsempty());
        builtin.addName(new Word("random"), new OpRandom());
        builtin.addName(new Word("sqrt"), new OpSqrt());
        builtin.addName(new Word("int"), new OpInt());
        builtin.addName(new Word("word"), new OpWord());
        builtin.addName(new Word("if"), new OpIf());
        builtin.addName(new Word("sentence"), new OpSentence());
        builtin.addName(new Word("list"), new OpList());
        builtin.addName(new Word("join"), new OpJoin());
        builtin.addName(new Word("first"), new OpFirst());
        builtin.addName(new Word("last"), new OpLast());
        builtin.addName(new Word("butfirst"), new OpButfirst());
        builtin.addName(new Word("butlast"), new OpButLast());
        builtin.addName(new Word("wait"), new OpWait());
        builtin.addName(new Word("poall"), new OpPoall());
        builtin.addName(new Word("erall"), new OpErall());
        builtin.addName(new Word("save"), new OpSave());
        builtin.addName(new Word("load"), new OpLoad());
        builtin.addName(new Word("run"), new OpRun());
        builtin.addName(new Word("pi"), new Word("3.1415926535"));

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


    private Scope global;
    private Scope builtin;
}
