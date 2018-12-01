package lib.util;

import lib.Number;
import lib.except.MUAExcept;
import lib.except.SyntaxError;
import lib.*;

import java.util.ArrayList;

public class ParserUtil {
    public static MUAObject parseObj(String str) throws SyntaxError {
        if (str.startsWith("\"") && str.length() > 1) {
            return new Word(str.substring(1));
        }
        else if (str.equals("false")) {
            return new Bool(false);
        }
        else if (str.equals("true")) {
            return new Bool(true);
        }
        else if (Character.isDigit(str.charAt(0))) {
            return new Number(Double.parseDouble(str));
        }
        else if (str.startsWith("[") && str.endsWith("]")) {
            ArrayList<MUAObject> content = new ArrayList<>();
            for (String item: str.substring(1, str.length() - 1).split("[ \t\n]")) {
                content.add(parseObj(item));
            }
            return new List(content);
        }
        else {
            throw new SyntaxError("Unknown syntax: '" + str + "'");
        }
    }


    public static ArrayList<String> parseToken(String str) throws SyntaxError {
        ArrayList<String> tokens = new ArrayList<>();
        String[] items = str.split("[ \t\n\r]");
        int count = 0;
        ArrayList<String> temp = new ArrayList<>();
        for (String item: items) {
            if (!item.startsWith("[") && !item.endsWith("]")) {
                if (count == 0)
                    tokens.add(item);
                else
                    temp.add(item);
            }
            else if (item.startsWith("[")) {
                count++;
                temp.add(item);
            }
            else if (item.endsWith("]")) {
                count--;
                temp.add(item);
                if (count < 0) {
                    throw new SyntaxError("Unpaired ']'");
                }
                else if (count == 0) {
                    tokens.add(String.join(" ", temp));
                    temp.clear();
                }
            }
        }
        if (count != 0) {
            throw new SyntaxError("Unpaired [");
        }
        return null;

    }
}
