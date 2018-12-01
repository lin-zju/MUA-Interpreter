package lib;

import lib.except.MUAExcept;

import java.util.ArrayList;

public class Erase extends Statement {
    public Erase(ArrayList<MUAObject> arglist) {
        super(Type.ERASE, arglist);
    }

    @Override
    public void exec(Scope scope) throws MUAExcept {

    }
}
