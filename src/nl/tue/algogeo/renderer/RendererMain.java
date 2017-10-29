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
            int size = getSizeFromLine(line);
            String[] labels = line.split(",");
            if (map == null) {
                map = new DotMap(size, size);
            }

            x = 0;

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

    private static int getSizeFromLine(String line) {
        int count = 1;
        for (int n = 0; n < line.length(); n += 1) {
            if (line.charAt(n) == ',')
                count += 1;
        }
        return count;
    }
}
