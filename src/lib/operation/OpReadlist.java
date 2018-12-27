package lib.operation;

import lib.Expr;
import lib.MuaObject;
import lib.Scope;
import lib.util.ArgUtil;

import static lib.util.ParserUtil.parseBasicObj;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class OpReadlist extends Expr {
    @Override
    public String getOpName() {
        return "readlist";
    }

    @Override
    public MuaObject eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
//        ArrayList<String> tokens = ParserUtil.parseToken(line);
//        ArrayList<MuaObject> objlist = ParserUtil.evalObj(tokens);
        return parseBasicObj("[ " + line + " ]");
    }

    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
    ));
    public int getArgNum() {
        return argtypes.size();
    }
}
