package lib;

abstract public class MuaObject {

    // type name
    abstract public String getTypeString();

    // get object value
    abstract public Object getValue();

    // string representation
    @Override
    abstract public String toString();
    public Scope enclosingScope = null;
}
