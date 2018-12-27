package lib;

import lib.error.SyntaxError;
import lib.operation.OpStop;
import lib.util.ArgUtil;
import lib.util.RunUtil;

import java.util.ArrayList;
import java.util.Arrays;

public class Func extends Expr {
    public Func(String str, Scope scope) throws Exception {
        name = str;
        MuaObject o = scope.getName(new Word(str));
        setUp(o);
        lexicalEnclosingScope = o.enclosingScope;
    }

    private void setUp(MuaObject o) throws Exception {
        if (!(o instanceof List)) {
            throw new SyntaxError("'" + name + "' is not a valid function");
        }
        ArrayList<MuaObject> objlist = ((List)o).getValue();
        if (objlist.size() != 2) {
            throw new SyntaxError("'" + name + "' is not a valid function");
        }
        if (!(objlist.get(0) instanceof List)) {
            throw new SyntaxError("'" + name + "' is not a valid function");
        }
        if (!(objlist.get(1) instanceof List)) {
            throw new SyntaxError("'" + name + "' is not a valid function");
        }
        for (MuaObject arg : ((List)objlist.get(0)).getValue()) {
//            System.out.println(arg);
//            MuaObject w = parseBasicObj(arg);
            if (!(arg instanceof Word)) {
                throw new SyntaxError("'" + name + "' is not a valid function");
            }
            argNames.add((Word)arg);
        }
        for (int i = 0; i < argNames.size(); i++) {
            argtypes.add(MuaObject.class);
        }

        body = ((List)objlist.get(1));
    }

    @Override
    public MuaObject eval(Scope scope, ArrayList<MuaObject> arglist) throws Exception {
        super.eval(scope, arglist);
        ArgUtil.argCheck(name, argtypes, arglist);
        Scope local = new Scope(name, Scope.Type.FUNCTION, lexicalEnclosingScope);
        for (int i = 0; i < argNames.size(); i++) {
            local.addName(argNames.get(i), arglist.get(i));
        }
        try {
              RunUtil.runList(local, body);
        }
        catch (OpStop.StopSignal e) {

        }
//        throw new SyntaxError("function not yet implemented");

        return local.getReturnValue();
    }


    @Override
    public String getOpName() {
        return name;
    }


    @Override
    public int getArgNum() {
        return argtypes.size();
    }

    final private ArrayList<Class> argtypes = new ArrayList<>(Arrays.asList(
    ));

    private String name;
    private ArrayList<Word> argNames = new ArrayList<>();
    private List body;
    private Scope lexicalEnclosingScope;
}
