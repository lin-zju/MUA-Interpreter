package lib;

abstract public class MUAObject {
    public enum Type {
        NUMBER,
        WORD,
        LIST,
        BOOL,
        EXPR,
        NONE,
        ANY;

        @Override
        public String toString() {
            switch (this) {
                case NUMBER: return "number";
                case WORD: return "word";
                case LIST: return "list";
                case BOOL: return "bool";
                case EXPR: return "expr";
                case NONE: return "none";
            }
            return "UNKNOWN";
        }
    }

    // constructor
    protected MUAObject(Type type) {
        this.type = type;
    }
    // get object type
    public Type getType() {
        return type;
    }
    // get object value
    abstract public Object getValue();
    // string representation
    @Override
    abstract public String toString();


    private Type type;

}
