package nl.tue.algogeo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DotMap {

    private int width;
    private int height;
    private Map<Integer, Dot> positionMap;

    public DotMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.positionMap = new HashMap<>();
    }

    public Dot get(int x, int y) {
        return this.positionMap.get(key(x, y));
    }

    /**
     * Returns all dots found in a rectangle on this dot map
     * @param xMin minimum x (inclusive)
     * @param yMin maximum x (inclusive)
     * @param xMax minimum y (inclusive)
     * @param yMax maximum y (inclusive)
     * @return list of dots found in the rectangle
     */
    public List<Dot> getRect(int xMin, int yMin, int xMax, int yMax) {
        List<Dot> result = new ArrayList<>();

        for (int y = 0; y <= yMax; y++) {
            for (int x = xMin; x <= xMax; x++) {
                Dot dot = get(x,y);
                if (dot != null) {
                    result.add(dot);
                }
            }
        }

        return result;
    }

    public void put(int x, int y, Dot dot) {
        this.positionMap.put(key(x, y), dot);
    }

    private int key(int x, int y) {
        if (x < 0 || x >= this.width) {
            throw new IllegalArgumentException("x out of bounds: 0 <= " + x + " <= " + (width - 1));
        }
        if (y < 0 || y >= this.height) {
            throw new IllegalArgumentException("y out of bounds: 0 <= " + y + " <= " + (height - 1));
        }

        return x * width + y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
