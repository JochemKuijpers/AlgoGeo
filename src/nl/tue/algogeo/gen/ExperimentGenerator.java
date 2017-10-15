package nl.tue.algogeo.gen;

import nl.tue.algogeo.Dot;
import nl.tue.algogeo.DotLabel;
import nl.tue.algogeo.DotLabelSet;
import nl.tue.algogeo.DotMap;

public class ExperimentGenerator implements DotMapGenerator {
    @Override
    public DotMap generate(int width, int height, int amount, DotLabelSet labels) {
        DotMap map = new DotMap(width, height);

        RecursiveNoise[] noisePerLabel = new RecursiveNoise[labels.size()];
        for (int i = 0; i < labels.size(); i++) {
            noisePerLabel[i] = new RecursiveNoise(width, height, 4);
        }

        RecursiveNoise populationNoise = new RecursiveNoise(width, height, 2);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (populationNoise.get(x, y) > 0.01) {
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
            }
        }

        return map;
    }
}
