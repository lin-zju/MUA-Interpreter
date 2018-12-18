package lib.operation;

import lib.Expr;
import lib.MUAObject;
import lib.Number;
import lib.Scope;
import lib.Word;
import lib.error.InputError;
import lib.util.ArgUtil;
import lib.util.Interpreter;
import lib.util.ParserUtil;

import java.util.ArrayList;
import java.util.Arrays;


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
        MUAObject obj =  ParserUtil.parseObj(tokens).get(0);
        if (!(obj instanceof Word) && !(obj instanceof Number))
            throw new InputError("input is not a number or word");
        return obj;
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
