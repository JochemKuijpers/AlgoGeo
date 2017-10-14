package nl.tue.algogeo;

public class Dot {
    private DotLabel label;
    private int amount;

    public Dot(DotLabel label, int amount) {
        this.label = label;
        this.amount = amount;
    }

    public DotLabel getLabel() {
        return label;
    }

    public int getAmount() {
        return amount;
    }
}
