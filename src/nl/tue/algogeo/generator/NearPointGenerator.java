package nl.tue.algogeo.generator;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

public class NearPointGenerator implements Generator {

    @Override
    public void generate(int size, int labels, long seed, BufferedWriter output) throws IOException {
        Random random = new Random();
        random.setSeed(seed);

        output.write("" + size + "\n");

        double labelPoints[][] = new double[labels][];
        for (int l = 0; l < labels; l += 1) {
            labelPoints[l] = new double[]{
                    random.nextDouble(),
                    random.nextDouble()
            };
        }

        for (int n = 0; n < size; n += 1) {
            int label = random.nextInt(labels);

            double lx = labelPoints[label][0];
            double ly = labelPoints[label][1];
            double x,y;
            do {
                x = random.nextDouble();
                y = random.nextDouble();
                double dist = Math.sqrt((lx - x) * (lx - x) + (ly - y) * (ly - y));
                double p = random.nextDouble() / 2;
                if (dist < p) {
                    output.write(
                        new DataPoint(
                            x, y, label
                        ) + "\n"
                    );
                    break;
                }
            } while (true);
        }

        output.flush();
    }
}
