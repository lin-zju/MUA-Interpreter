import lib.Expr;
import lib.MUAObject;
import lib.Scope;

import static lib.util.ParserUtil.parseObj;
import static lib.util.ParserUtil.parseToken;

public class Main {
    public static void main(String[] args) throws Exception {
        String hello = "make \"hi 23";
        String kk = "make \"hi read";
        String yes = "print :hi";
        Expr hi = (Expr)parseObj(parseToken(hello)).get(0);
        Expr gg = (Expr)parseObj(parseToken(kk)).get(0);
        Expr no = (Expr)parseObj(parseToken(yes)).get(0);
        Scope scope = new Scope();
        hi.eval(scope);
        gg.eval(scope);
        no.eval(scope);

    }

}
