package lib;

import lib.except.MUAExcept;
import lib.util.ParserUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Readlist extends Expr {
    public Readlist(ArrayList<MUAObject> arglist) {
        super(SubType.READLIST, arglist, argtypes);
    }

    @Override
    public MUAObject eval(Scope scope) throws Exception {
        super.eval(scope);
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        while (line.trim().equals("")) {
            line = input.nextLine();
        }
        ArrayList<MUAObject> content = new ArrayList<>();
        for (String token: ParserUtil.parseToken(line)) {
            content.add(ParserUtil.parseBasicObj(token));
        }
        return new List(content);
    }

    final static private ArrayList<MUAObject.Type> argtypes = new ArrayList<MUAObject.Type>(Arrays.asList(
    ));
    public static int getArgNum() {
        return argtypes.size();
    }
}
