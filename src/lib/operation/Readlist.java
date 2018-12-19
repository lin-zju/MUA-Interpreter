package lib.operation;

import lib.Expr;
import lib.List;
import lib.MUAObject;
import lib.Scope;
import lib.util.ArgUtil;
import lib.util.Interpreter;
import lib.util.ParserUtil;
import static lib.util.ParserUtil.parseBasicObj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Readlist extends Expr {
    @Override
    public String getOpName() {
        return "readlist";
    }

    @Override
    public MUAObject eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
//        ArrayList<String> tokens = ParserUtil.parseToken(line);
//        ArrayList<MUAObject> objlist = ParserUtil.parseObj(tokens);
        return parseBasicObj("[ " + line + " +]");
    }

    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
    ));
    public int getArgNum() {
        return argtypes.size();
    }
}
