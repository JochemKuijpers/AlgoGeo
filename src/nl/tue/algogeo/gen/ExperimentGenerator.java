package nl.tue.algogeo.gen;

import nl.tue.algogeo.*;

import java.util.Random;

public class ExperimentGenerator implements DotMapGenerator {
    @Override
    public DotMap generate(int width, int height, int amount, DotLabelSet labels) {
        DotMap map = new DotMap(width, height);

        RecursiveNoise[] noisePerLabel = new RecursiveNoise[labels.size()];
        for (int i = 0; i < labels.size(); i++) {
            noisePerLabel[i] = new RecursiveNoise(width, height, 2);
        }

        RecursiveNoise populationNoise = new RecursiveNoise(width, height, 2);
        Random random = new Random();
        random.setSeed(System.nanoTime());

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (populationNoise.get(x, y) > 0.5) {
                    DotLabel label = null;
                    double labelVal = Double.NEGATIVE_INFINITY;

                    for (int i = 0; i < noisePerLabel.length; i++) {
                        if (labelVal < noisePerLabel[i].get(x, y)) {
                            labelVal = noisePerLabel[i].get(x, y);
                            label = labels.get(i);
                        }
                    }
                    map.put(x, y, new Dot(label, amount));
                }
                // map.put(x, y, new Dot(new DotLabel(Utils.merge(Color.BLACK, Color.WHITE, (float) populationNoise.get(x, y)), ""), amount));
            }
        }

        return map;
    }
}
