//package lib.operation;
//
//public class Repeat {
//    @Override
//    public String getOpName() {
//        return "repeat";
//    }
//
//    @Override
//    public Bool eval(Scope scope) throws Exception {
//        super.eval(scope);
//        ArgUtil.argCheck(getOpName(), argtypes, arglist);
//        Word obj = (Word)arglist.get(0);
//        return new Bool(scope.hasName(obj));
//    }
//
//    final static private ArrayList<Class> argtypes = new ArrayList<Class>(Arrays.asList(
//            Word.class
//    ));
//    public int getArgNum() {
//
//    }
