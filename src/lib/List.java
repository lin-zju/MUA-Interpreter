package lib;

import java.util.ArrayList;

public class List extends MuaObject {
    public List(ArrayList<MuaObject> list) {
        this.value = list;
    }

    @Override
    public String toString() {
        String content = "";
        boolean first = true;
        for (MuaObject v: value) {
            content += (first ? "" : " ") + v.toString();
            first = false;
        }
        return "[" + content + "]";
    }

    @Override
    public String getTypeString() {
        return "list";
    }

    @Override
    public ArrayList<MuaObject> getValue() {
        return value;
    }

    private ArrayList<MuaObject> value;
}
