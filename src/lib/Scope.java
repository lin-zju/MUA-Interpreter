package lib;
import lib.error.NameError;

import java.util.HashMap;

public class Scope {
    enum Type {
        GLOBAL,
        FUNCTION
    }

    // by default create a global scope
    public Scope() {
        this("global", Type.GLOBAL, null);

    }

    // create a function scope
    public Scope(String name, Type type, Scope enclosing) {
        this.scopeName = name;
        this.scopeType = type;
        this.enclosingScope = enclosing;
    }


    public String getScopeName() {
        return scopeName;
    }

    public Type getScopeType() {
        return scopeType;
    }

    public Scope getEnclosingScope() {
        return null;
    }

    public void addName(Word name, MUAObject value) {
        scope.put(name.getValue(), value);
    }

    public MUAObject getName(Word name) throws NameError {
        MUAObject ret = scope.get(name.toString());
        if (ret == null) {
            throw new NameError("name '" + name.getValue() + "' not found");
        }
        return ret;

    }

    public void removeName(Word name) throws NameError {
        MUAObject succeed = scope.remove(name.getValue());
        if (succeed == null) {
            throw new NameError("name '" + name.getValue() + "' not found");
        }
    }

    public boolean hasName(Word name) {
        return scope.containsKey(name.getValue());
    }

    private String scopeName = "global";
    private Type scopeType = Type.GLOBAL;
    private Scope enclosingScope = null;
    private HashMap<String, MUAObject> scope = new HashMap<>();
}
