package nl.tue.algogeo.generator;

import nl.tue.algogeo.Color;
import nl.tue.algogeo.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GeneratorMain {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            showHelp();
        }

        String gen = "uniform";
        String filename = "output.txt";
        int labels = 4;
        int size = 1024;
        long seed = 0;
        boolean error = false;

        Map<String, Generator> gens = new HashMap<>();
        gens.put("uniform", new UniformGenerator());
        gens.put("nearpoint", new NearPointGenerator());
        gens.put("checker", new CheckerGenerator());

        for (String arg: args) {
            String[] argParts = arg.split("=", 2);
            if (argParts.length == 0) {
                System.err.println("Invalid argument: " + arg);
                error = true;
            }

            switch(argParts[0]) {
                case "--gen":
                    if (gens.containsKey(argParts[1])) {
                        gen = argParts[1];
                    } else {
                        System.err.println("Invalid value for --gen parameter.");
                        error = true;
                    }
                    break;
//                case "--file":
//                    filename = argParts[1];
//                    break;
                case "--size":
                    try {
                        size = Integer.parseInt(argParts[1]);
                        if (size > 65536 || size < 1) {
                            throw new IllegalArgumentException();
                        }
                    } catch (Exception e) {
                        System.err.println("Invalid value for --size parameter.");
                        error = true;
                    }
                    break;
                case "--labels":
                    try {
                        labels = Integer.parseInt(argParts[1]);
                        if (labels > 9 || labels < 1) {
                            throw new IllegalArgumentException();
                        }
                    } catch (Exception e) {
                        System.err.println("Invalid value for --labels parameter.");
                        error = true;
                    }
                    break;
                case "--seed":
                    try {
                        seed = Long.parseLong(argParts[1]);
                    } catch (Exception e) {
                        System.err.println("Invalid value for --seed parameter.");
                        error = true;
                    }
                    break;
                default:
                    System.err.println("Unknown parameter " + arg);
                    error = true;
            }
        }

        filename = gen + "-" + size + "-" + labels + "-" + seed + ".txt";

        if (error) {
            showHelp();
        } else {
            OutputStream output = new FileOutputStream(filename);
            gens.get(gen).generate(size, labels, seed, new BufferedWriter(new OutputStreamWriter(output)));
            output.close();

            System.out.println("Done.");
        }

        render(filename);
    }

    private static void render(String filename) throws IOException {
        InputStream input = new FileInputStream(filename);
        Scanner scanner = new Scanner(input);
        BufferedImage image = new BufferedImage(1000, 1000, BufferedImage.TYPE_INT_RGB);
        for (int y = 0; y < 1000; y++) {
            for (int x = 0; x < 1000; x++) {
                image.setRGB(x, y, 0xFFFFFF);
            }
        }
        DataPoint dataPoint = null;

        for (int n = scanner.nextInt(); n > 0; n -= 1) {
            dataPoint = new DataPoint(scanner.nextDouble(), scanner.nextDouble(), scanner.nextInt());
            Color color = Color.get(dataPoint.getLabel());

            for (int dy = -1; dy <= 1; dy++) {
                for (int dx = -1; dx <= 1; dx += 1) {
                    image.setRGB(
                            Math.min(Math.max((int) (dataPoint.getX() * 1000) + dx, 0), 999),
                            Math.min(Math.max((int) (dataPoint.getY() * 1000) + dy, 0), 999),
                            Utils.colorToInt(
                                    Utils.gamma(color.r, 2.2f),
                                    Utils.gamma(color.g, 2.2f),
                                    Utils.gamma(color.b, 2.2f)
                            )
                    );
                }
            }
        }

        ImageIO.write(image, "png", new FileOutputStream(filename + ".png"));

    }

    private static void showHelp() {
        System.out.println("Usage:");
        System.out.println("");
        System.out.println("algo-gen --gen=[uniform] --file=[outputfile] --labels=[1-9] --size=[1-65536]");
        System.out.println("");
        System.out.println("  --gen         Type of generation algorithm to use");
        System.out.println("    uniform     Use a uniform distribution.");
        System.out.println("    nearpoint   Generate datapoints near a point for every label.");
        System.out.println("    checker     Generate datapoints in a checkerboard pattern.");
        System.out.println("");
//        System.out.println("  --file        Output file for the generated data set");
//        System.out.println("                Defaults to output.txt");
//        System.out.println("");
        System.out.println("  --labels      The amount of labels to generate");
        System.out.println("                Defaults to 4");
        System.out.println("");
        System.out.println("  --size        The amount of datapoints to generate");
        System.out.println("                Defaults to 1024");
        System.out.println("");
        System.out.println("  --seed        The random seed used in generating the data");
        System.out.println("                Defaults to 0");
    }
}
