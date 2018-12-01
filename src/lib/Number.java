package lib;

public class Number extends MUAObject {
    public Number(double d) {
        super(Type.NUMBER);
        this.value = d;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public Double getValue() {
        return value;
    }

    private double value;
}
