package lib;

import java.util.ArrayList;

public class List extends MUAObject {
    public List(ArrayList<MUAObject> list) {
        super(Type.LIST);
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
        return "[" + content + "]";
    }

    @Override
    public Object getValue() {
        return value;
    }

    private ArrayList<MUAObject> value;
}
