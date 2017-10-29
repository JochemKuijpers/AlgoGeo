package nl.tue.algogeo;

import com.sun.org.apache.regexp.internal.RE;

public class Color {
    public static final Color RED = new Color(1.0f, 0.0f, 0.0f);
    public static final Color ORANGE = new Color(1.0f, 0.3f, 0.0f);
    public static final Color YELLOW = new Color(1.0f, 0.8f, 0.0f);
    public static final Color LIME = new Color(0.0f, 1.0f, 0.0f);
    public static final Color GREEN = new Color(0.0f, 0.5f, 0.0f);
    public static final Color CYAN = new Color(0.0f, 1.0f, 1.0f);
    public static final Color BLUE = new Color(0.0f, 0.0f, 1.0f);
    public static final Color PURPLE = new Color(0.5f, 0.0f, 1.0f);
    public static final Color MAGENTA = new Color(1.0f, 0.0f, 1.0f);
    public static final Color WHITE = new Color(1.0f, 1.0f, 1.0f);
    public static final Color BLACK = new Color(0.0f, 0.0f, 0.0f);

    public float r;
    public float g;
    public float b;

    public Color(int r, int g, int b) {
        this((float) r, (float) g, (float) b);
    }

    public Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public static Color get(int n) {
        switch (n) {
            case 0: return RED;
            case 1: return YELLOW;
            case 2: return GREEN;
            case 3: return BLUE;
            case 4: return MAGENTA;
            case 5: return ORANGE;
            case 6: return LIME;
            case 7: return CYAN;
            case 8: return PURPLE;
            case 9: return BLACK;
            default: return BLACK;
        }
    }
}
