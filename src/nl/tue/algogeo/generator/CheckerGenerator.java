package nl.tue.algogeo.generator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

public class CheckerGenerator implements Generator {
    @Override
    public void generate(int size, int labels, long seed, BufferedWriter output) throws IOException {
        Random random = new Random();
        random.setSeed(seed);

        output.write("" + size + "\n");

        for (int n = 0; n < size; n += 1) {
            double x = 0;
            double y = 0;
            int label = random.nextInt(labels);
            do {
                x = random.nextDouble();
                y = random.nextDouble();
                if ((int) (Math.floor(x * 8) + Math.floor(y * 8)) % labels == label) {
                    break;
                }
            } while (true);
            output.write(
                    new DataPoint(x, y, label) + "\n"
            );
        }

        output.flush();
    }
}
