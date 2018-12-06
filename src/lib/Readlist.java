package lib;

import lib.util.ArgUtil;
import lib.util.ParserUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Readlist extends Expr {
    public Readlist() {
        super(SubType.READLIST);
    }

    @Override
    public MUAObject eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getName(), argtypes, arglist);
        String line = Interpreter.getLine(false);
        ArrayList<String> tokens = ParserUtil.parseToken(line);
//        ArrayList<MUAObject> objlist = ParserUtil.parseObj(tokens);
        return new List(tokens);
    }

    final static private ArrayList<MUAObject.Type> argtypes = new ArrayList<MUAObject.Type>(Arrays.asList(
    ));
    public int getArgNum() {
        return argtypes.size();
    }
}
