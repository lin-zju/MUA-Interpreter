package lib;

import lib.except.MUAExcept;
import lib.util.ParserUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Read extends Expr {
    public Read(ArrayList<MUAObject> arglist) {
        super(SubType.READ, arglist, argtypes);
    }

    @Override
    public MUAObject eval(Scope scope) throws Exception {
        super.eval(scope);
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        while (line.trim().equals("")) {
            line = input.nextLine();
        }
        ArrayList<String> tokens = ParserUtil.parseToken(line);
        return ParserUtil.parseBasicObj(tokens.get(0));
    }

    static final private ArrayList<MUAObject.Type> argtypes = new ArrayList<MUAObject.Type>(Arrays.asList(
    ));
    public static int getArgNum() {
        return argtypes.size();
    }
}
