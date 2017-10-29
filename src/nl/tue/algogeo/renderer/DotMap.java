package nl.tue.algogeo.renderer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DotMap {

    private int width;
    private int height;
    private Map<Integer, Integer> positionMap;

    public DotMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.positionMap = new HashMap<>();
    }

    public Integer get(int x, int y) {
        return this.positionMap.get(key(x, y));
    }

    public void put(int x, int y, Integer dot) {
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
