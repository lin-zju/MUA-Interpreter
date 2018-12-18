package lib;

abstract public class MUAObject {
//    public enum Type {
//        NUMBER,
//        WORD,
//        LIST,
//        BOOL,
//        EXPR,
//        NONE,
//        ANY;
//
//        @Override
//        public String toString() {
//            switch (this) {
//                case NUMBER: return "number";
//                case WORD: return "word";
//                case LIST: return "list";
//                case BOOL: return "bool";
//                case EXPR: return "expr";
//                case NONE: return "none";
//            }
//            return "UNKNOWN";
//        }
//    }

    abstract public String getTypeString();

    // get object value
    abstract public Object getValue();

    // string representation
    @Override
    abstract public String toString();
}
