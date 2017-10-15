package nl.tue.algogeo.gen;

public abstract class NoiseMap {

    /**
     * A 2D map containing noise at integer coordinates
     */
    protected double[][] map;

    /**
     * @param x x coord
     * @param y y coord
     * @return noise value at (x,y), linearly interpolated for non-integer coordinates
     */
    public double get(double x, double y) {
        int x1 = ((int) x) % map[0].length;
        int x2 = (x1 + 1) % map[0].length;
        int y1 = ((int) y) % map.length;
        int y2 = (y1 + 1) % map.length;

        double wx = x - Math.floor(x);
        double wy = y - Math.floor(y);

        double t = (1 - wx) * map[y1][x1] + wx * map[y1][x2];
        double b = (1 - wx) * map[y2][x1] + wx * map[y2][x2];
        return (1 - wy) * t + wy * b;
    }
}
