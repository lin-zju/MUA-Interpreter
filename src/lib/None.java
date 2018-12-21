package lib;


public class None extends MUAObject {
    // ctor
    public None() {
    }

    @Override
    public String getTypeString() {
        return "none";
    }

    @Override
    public None getValue() {
        return this;
    }

    @Override
    public String toString() {
        return "None";
    }

}
