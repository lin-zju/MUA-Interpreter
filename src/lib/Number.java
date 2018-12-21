package lib;

public class Number extends MUAObject {
    public Number(double d) {
        this.value = d;
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }

    @Override
    public String getTypeString() {
        return "number";
    }

    @Override
    public Double getValue() {
        return value;
    }

    private double value;
}
