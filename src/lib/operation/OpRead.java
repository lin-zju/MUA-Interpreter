package lib.operation;

import lib.Expr;
import lib.MuaObject;
import lib.Scope;
import lib.Word;
import lib.util.ArgUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class OpRead extends Expr {

    @Override
    public MuaObject eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        Scanner input = new Scanner(System.in);
        String token = input.next();
//        String line = Interpreter.getLine(false);
//        ArrayList<String> tokens = ParserUtil.parseToken(line);
//        MuaObject obj =  ParserUtil.evalObj(tokens, scope).get(0);
//        if (!(obj instanceof Word) && !(obj instanceof Number))
//            throw new InputError("input is not a number or word");
        return new Word(token);
    }
    @Override
    public String getOpName() {
        return "read";
    }

    static final private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
    ));
    public int getArgNum() {
        return argtypes.size();
    }
}
