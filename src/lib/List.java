package lib;

import java.util.ArrayList;

public class List extends MUAObject {
    public List(ArrayList<MUAObject> list) {
        this.value = list;
    }

    @Override
    public String toString() {
        String content = "";
        boolean first = true;
        for (MUAObject v: value) {
            content += (first ? "" : " ") + v.toString();
            first = false;
        }
//        return String.join(" ", value);
        return "[" + content + "]";
    }

    @Override
    public String getTypeString() {
        return "list";
    }

    @Override
    public ArrayList<MUAObject> getValue() {
        return value;
    }

    private ArrayList<MUAObject> value;
}
