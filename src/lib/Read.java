package lib;

import lib.util.ArgUtil;
import lib.util.ParserUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Read extends Expr {
    public Read() {
        super(SubType.READ);
    }

    @Override
    public MUAObject eval(Scope scope) throws Exception {
        super.eval(scope);
        ArgUtil.argCheck(getName(), argtypes, arglist);
        String line = Interpreter.getLine(false);
        ArrayList<String> tokens = ParserUtil.parseToken(line);
        return ParserUtil.parseObj(tokens).get(0);
    }
    @Override
    public String getName() {
        return "func";
    }

    static final private ArrayList<MUAObject.Type> argtypes = new ArrayList<MUAObject.Type>(Arrays.asList(
    ));
    public int getArgNum() {
        return argtypes.size();
    }
}
