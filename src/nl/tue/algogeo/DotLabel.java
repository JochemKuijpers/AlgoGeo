package nl.tue.algogeo;

public class DotLabel {
    private Color color;
    private String text;

    public DotLabel(Color color, String text) {
        this.color = color;
        this.text = text;
    }

    public Color getColor() {
        return color;
    }

    public String getText() {
        return text;
    }
}
