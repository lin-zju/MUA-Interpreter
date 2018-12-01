package lib;

abstract public class Expression extends Statement {
    abstract public MUAObject eval(Scope scope);
    @Override
    public void exec(Scope scope) {
        System.out.println(eval(scope));
    }

}
