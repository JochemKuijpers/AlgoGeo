package nl.tue.algogeo.generator;

import java.io.BufferedWriter;
import java.io.IOException;

public interface Generator {
    void generate(int size, int labels, long seed, BufferedWriter output) throws IOException;
}
