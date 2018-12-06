package lib;


public class None extends MUAObject {
    // ctor
    public None() {
        super(MUAObject.Type.NONE);
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
