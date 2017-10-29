package nl.tue.algogeo.generator;

public class DataPoint {
    private double x;
    private double y;
    private int label;

    public DataPoint(double x, double y, int label) {
        this.x = x;
        this.y = y;
        this.label = label;
    }

    @Override
    public String toString() {
        return x + " " + y + " " + label;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getLabel() {
        return label;
    }
}
