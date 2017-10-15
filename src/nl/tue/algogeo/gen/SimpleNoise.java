package nl.tue.algogeo.gen;

import java.util.Random;

public class SimpleNoise extends NoiseMap {

    public SimpleNoise(int width, int height) {
        Random random = new Random();
        random.setSeed(System.nanoTime());

        map = new double[height][];
        for (int y = 0; y < height; y++) {
            map[y] = new double[width];
            for (int x = 0; x < width; x++) {
                map[y][x] = random.nextDouble() * 2 - 1;
            }
        }
    }
}
