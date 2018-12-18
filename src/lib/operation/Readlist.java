package lib.operation;

import lib.Expr;
import lib.List;
import lib.MUAObject;
import lib.Scope;
import lib.util.ArgUtil;
import lib.util.Interpreter;
import lib.util.ParserUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Readlist extends Expr {
    @Override
    public String getOpName() {
        return "readlist";
    }

    @Override
    public MUAObject eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        String line = Interpreter.getLine(false);
        ArrayList<String> tokens = ParserUtil.parseToken(line);
//        ArrayList<MUAObject> objlist = ParserUtil.parseObj(tokens);
        return new List(tokens);
    }

    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
    ));
    public int getArgNum() {
        return argtypes.size();
    }
}
