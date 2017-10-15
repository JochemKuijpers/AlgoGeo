package nl.tue.algogeo.gen;

import nl.tue.algogeo.Dot;
import nl.tue.algogeo.DotLabelSet;
import nl.tue.algogeo.DotMap;

import java.util.Random;

public class SimpleGenerator implements DotMapGenerator {
    @Override
    public DotMap generate(int width, int height, int amount, DotLabelSet labels) {
        DotMap map = new DotMap(width, height);
        Random random = new Random();
        random.setSeed(System.nanoTime());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (random.nextBoolean()) {
                    Dot dot = new Dot(labels.get(random.nextInt(labels.size())), amount);
                    map.put(x, y, dot);
                }
            }
        }

        return map;
    }
}
