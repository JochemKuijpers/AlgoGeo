package nl.tue.algogeo.gen;

import nl.tue.algogeo.DotLabelSet;
import nl.tue.algogeo.DotMap;

public interface DotMapGenerator {
    DotMap generate(int width, int height, int amount, DotLabelSet labels);
}
