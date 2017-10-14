package nl.tue.algogeo;

public class Utils {
    public static int gamma(float linear, float gamma) {
        return (int) Utils.clamp(0, (float) (256f * Math.pow(linear, 1f / gamma)), 255);
    }

    public static int colorToInt(int r, int g, int b) {
        return r << 16  | g << 8 | b;
    }

    public static float clamp(float min, float x, float max) {
        if (x < min) {
            return min;
        }
        if (x > max) {
            return max;
        }
        return x;
    }

    public static Color merge(Color a, Color b, float mix) {
        mix = clamp(0, mix, 1);
        return new Color(
            a.r * (1-mix) + b.r * mix,
            a.g * (1-mix) + b.g * mix,
            a.b * (1-mix) + b.b * mix
        );
    }
}