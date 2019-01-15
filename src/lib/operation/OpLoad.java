package lib.operation;

import lib.*;
import lib.error.IOError;
import lib.util.ArgUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class OpLoad extends Expr {
    @Override
    public String getOpName() {
        return "load";
    }

    @Override
    public None eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        Word w = (Word)arglist.get(0);
        File file = new File(w.getValue());
        try {
            ObjectInputStream input =
                    new ObjectInputStream(new FileInputStream(file));
            Scope newScope = (Scope) input.readObject();
            scope.addAllName(newScope);
            input.close();

        } catch (IOException e1) {
            throw new IOError(e1.getMessage());
        }

        return new None();
    }


    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
            Word.class
    ));

    public int getArgNum() {
        return argtypes.size();
    }
}
