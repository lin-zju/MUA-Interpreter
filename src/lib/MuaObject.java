package lib;

import java.io.Serializable;

abstract public class MuaObject implements Serializable {

    // type name
    abstract public String getTypeString();

    // get object value
    abstract public Object getValue();

    // string representation
    @Override
    abstract public String toString();
    public Scope enclosingScope = null;
}
