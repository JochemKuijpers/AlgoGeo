package nl.tue.algogeo.gen;

import java.util.Random;

public class RecursiveNoise extends NoiseMap {

    public RecursiveNoise(int width, int height, int skipLevels) {
        Random random = new Random();
        random.setSeed(System.nanoTime());

        int levels = (int) Math.ceil(Math.log(Math.max(width, height)) / Math.log(2.0));
        SimpleNoise[] noiseMaps = new SimpleNoise[levels];

        for (int level = skipLevels; level < levels; level++) {
            noiseMaps[level] = new SimpleNoise(1 << (level+1), 1 << (level+1));
        }

        map = new double[height][];
        for (int y = 0; y < height; y++) {
            map[y] = new double[width];
            for (int x = 0; x < width; x++) {
                double value = 0;

                for (int level = skipLevels; level < levels; level++) {
                    double size = 1 << (level + 1);
                    double times = (1 << levels) / size;
                    value += noiseMaps[level].get(x / times, y / times) * (1 / size);
                }

                map[y][x] = value / 2;
            }
        }
    }
}
