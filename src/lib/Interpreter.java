package lib;

import lib.except.MUAExcept;
import lib.except.SyntaxError;

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
    public String getLine() throws Exception {
        Scanner input = new Scanner(System.in);
        promptOne();
        String line = input.nextLine();
        while (line.trim().equals("")) {
            promptOne();
            line = input.nextLine();
        }
        while (true) {
            int count = 0;
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) == '[') {
                    count++;
                }
                else if (line.charAt(i) == ']') {
                    count--;
                }
                if (count < 0) {
                    throw new SyntaxError("Unpaired ']'");
                }
            }
            if (count != 0) {
                promptTwo();
                String temp = input.nextLine();
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

    public void next()  {
        try {
            String line = getLine();
            evalLine(line);
        }
        catch (MUAExcept e) {
            System.out.println(e.getMessage());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void promptOne() {
        System.out.print(pOne);
    }
    public static void promptTwo() {
        System.out.print(pTwo);
    }


    private Scope global = new Scope();
}
