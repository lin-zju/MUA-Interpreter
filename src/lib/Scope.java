package lib;
import lib.error.NameError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Scope implements Serializable {

    public enum Type {
        GLOBAL,
        FUNCTIONAL,
        BUILTIN
    }

    // create a function scope
    public Scope(String name, Type type, Scope enclosing) {
        this.scopeName = name;
        this.enclosingScope = enclosing;
        this.type = type;
    }


    public Type getType() {
        return type;
    }
    public String getScopeName() {
        return scopeName;
    }

    public Scope getEnclosingScope() {
        return enclosingScope;
    }

    public void addName(Word name, MuaObject value) {
        scope.put(name.getValue(), value);
        if (value.enclosingScope == null) {
            value.enclosingScope = this;
        }
    }

    public MuaObject getName(Word name) throws NameError {
        MuaObject ret = scope.get(name.toString());
        if (ret == null) {
            if (enclosingScope == null) {
                throw new NameError("name '" + name.getValue() + "' not found");
            }
            else
                return enclosingScope.getName(name);
        }
        return ret;

    }


    public void removeName(Word name) throws NameError {
        MuaObject succeed = scope.remove(name.getValue());
        // remove global
        if (succeed == null) {
            if (enclosingScope != null) {
                enclosingScope.removeName(name);
            } else {
                throw new NameError("name '" + name.getValue() + "' not found");
            }
        }
    }

    public boolean hasName(Word name) {
        if (!scope.containsKey(name.getValue())) {
            if (enclosingScope == null)
                return false;
            else
                return enclosingScope.hasName(name);
        }
        else
            return true;
    }

    public void setReturnValue(MuaObject o) {
        returnValue = o;
    }

    public MuaObject getReturnValue() {
        return returnValue;
    }

    public Set<String> getAllName() {
        return scope.keySet();
    }

    public void deleteAllName() {
        scope.clear();
    }

    public void addAllName(Scope newScope) {
        for (MuaObject o : newScope.scope.values()) {
            o.enclosingScope = this;
        }
        scope.putAll(newScope.scope);
    }

    private String scopeName = "global";
    private Scope enclosingScope = null;
    private Type type;
    private MuaObject returnValue = new None();

    private HashMap<String, MuaObject> scope = new HashMap<>();
}
