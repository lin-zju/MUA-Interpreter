package lib;

import lib.except.MUAExcept;
import lib.util.ArgUtil;
import lib.util.ParserUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Readlist extends Expression {
    public Readlist(ArrayList<MUAObject> arglist) {
        super(Type.READ, arglist);
    }

    @Override
    public MUAObject eval(Scope scope) throws MUAExcept {
        ArgUtil.argCheck(getName(), typelist, arglist);
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        while (line.trim().equals("")) {
            line = input.nextLine();
        }
        ArrayList<MUAObject> content = new ArrayList<>();
        for (String token: ParserUtil.parseToken(line)) {
            content.add(ParserUtil.parseObj(token));
        }
        return new List(content);
    }

    private ArrayList<MUAObject.Type> typelist = new ArrayList<MUAObject.Type>(Arrays.asList(
    ));
}
