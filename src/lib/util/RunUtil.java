package lib.util;

import lib.Expr;
import lib.List;
import lib.MUAObject;
import lib.Scope;
import static lib.util.ParserUtil.parseObj;
import java.util.ArrayList;

public class RunUtil {
    public static MUAObject runList(Scope scope, List l) throws Exception {
        ArrayList<String> tokens = new ArrayList<>();
        for (MUAObject token : l.getValue()) {
            tokens.add(token.toString());
        }
        ArrayList<MUAObject> objlist = parseObj(tokens, scope);
        for (MUAObject obj : objlist) {
            if (obj instanceof Expr) {
                ((Expr)obj).eval(scope);
            }
            if (scope.getStopFlag()) {
                return scope.getReturnValue();
            }
        }
        return scope.getReturnValue();
    }
}
