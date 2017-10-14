package nl.tue.algogeo;

public interface DotMapGenerator {
    DotMap generate(int width, int height, int amount, DotLabelSet labels);
}
