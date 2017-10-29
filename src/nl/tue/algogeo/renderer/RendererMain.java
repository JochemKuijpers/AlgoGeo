package nl.tue.algogeo.renderer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class RendererMain {

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("This program requires one parameter: the input file");
        }

        InputStream input = new FileInputStream(args[0]);
        Scanner scanner = new Scanner(input);

        int k = scanner.nextInt();
        scanner.nextLine();

        DotMap map = null;

        int x;
        int y = 0;

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] labels = line.split(",");
            x = 0;
            if (map == null) {
                map = new DotMap(labels.length, labels.length);
            }

            for (String label : labels) {
                if (!label.equals("")) {
                    map.put(x, y, Integer.parseInt(label));
                }
                x += 1;
            }
            y += 1;
        }

        DotMapRenderer renderer = new DotMapRenderer();
        renderer.renderPng(map, (int) Math.ceil(1000.0 / map.getWidth()), "output.png");
    }
}
