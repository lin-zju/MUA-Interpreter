package lib;

abstract public class MUAObject {
    public enum Type {
        NUMBER,
        WORD,
        LIST,
        BOOL
    }

    private Type type;


    protected MUAObject(Type type) {
        this.type = type;
    }


    public Type getType() {
        return type;
    }

    @Override
    abstract public String toString();

    abstract public Object getValue();

}
