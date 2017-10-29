package nl.tue.algogeo.generator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

public class UniformGenerator implements Generator {
    @Override
    public void generate(int size, int labels, long seed, BufferedWriter output) throws IOException {
        Random random = new Random();
        random.setSeed(seed);

        output.write("" + size + "\n");

        for (int n = 0; n < size; n += 1) {
            output.write(
                new DataPoint(
                    random.nextDouble(),
                    random.nextDouble(),
                    random.nextInt(labels)
                ) + "\n"
            );
        }

        output.flush();
    }
}
