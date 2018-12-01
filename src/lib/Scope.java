package lib;
import java.util.HashMap;

public class Scope {
    enum Type {
        GLOBAL,
        FUNCTION
    }

    public Scope() {
        this("global", Type.GLOBAL, null);

    }

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
        scope.put(name.toString(), value);
    }

    public MUAObject getName(Word name) throws Exception {
        MUAObject ret = scope.get(name.toString());
        if (ret == null) {
            throw new Exception("name" + name.toString() + "not found");
        }
        return ret;

    }

    public void removeName(Word name) throws Exception {
        MUAObject succeed = scope.remove(name.toString());
        if (succeed == null) {
            throw new Exception("name" + name.toString() + "not found");
        }

    }

    private String scopeName = "global";
    private Type scopeType = Type.GLOBAL;
    private Scope enclosingScope = null;
    private HashMap<String, MUAObject> scope = new HashMap<>();
}
