package lib.operation;

import lib.*;
import lib.error.IOError;
import lib.util.ArgUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class OpSave extends Expr {
    @Override
    public String getOpName() {
        return "save";
    }

    @Override
    public None eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(getOpName(), argtypes, arglist);
        Word w = (Word)arglist.get(0);
        File file = new File(w.getValue());
        try {
            ObjectOutputStream output =
                    new ObjectOutputStream((new FileOutputStream(file)));
            output.writeObject(scope);
            output.close();
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
