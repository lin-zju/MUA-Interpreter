package lib;
import lib.except.ArgError;
import java.util.ArrayList;
import java.util.Arrays;
import lib.util.ArgUtil;

public class Make extends Statement {

    public Make(ArrayList<MUAObject> arglist) {
        super(Type.MAKE, arglist);
    }

    @Override
    public void exec(Scope scope) throws ArgError {
        ArgUtil.argCheck(getName(), typelist, arglist);
        Word word = (Word) arglist.get(0);
        MUAObject value = arglist.get(1);
        scope.addName(word, value);

    }


    private ArrayList<MUAObject.Type> typelist = new ArrayList<MUAObject.Type>(Arrays.asList(
            MUAObject.Type.WORD,
            MUAObject.Type.ANY
    ));






}
