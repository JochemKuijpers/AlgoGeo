package nl.tue.algogeo;

import nl.tue.algogeo.algo.DotMapScaler;
import nl.tue.algogeo.algo.SampleAlgo;
import nl.tue.algogeo.gen.DotMapGenerator;
import nl.tue.algogeo.gen.ExperimentGenerator;
import nl.tue.algogeo.gen.SimpleGenerator;
import nl.tue.algogeo.renderer.DotMapRenderer;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    private void run() throws IOException {

        System.out.println("Generating labels ...");
        DotLabelSet labels = DotLabelSet.create(4);

        System.out.println("Generating dot map ...");
        DotMapGenerator generator = getGenerator();
        DotMap originalMap = generator.generate(128, 128, 10, labels);

        System.out.println("Rendering original dotmap...");
        DotMapRenderer renderer = new DotMapRenderer();
        int pixelsPerDot = 16;
        int maxZoom = 6;
        renderer.renderPng(originalMap, pixelsPerDot, "original.png");
        DotMapScaler scaler = getScaler();

        for (int zoom = 1; zoom < maxZoom; zoom++) {

            System.out.println("Calculating zoom level: " + zoom + " ...");
            DotMap scaledMap = scaler.scaleDotMap(originalMap,
                (int) (originalMap.getWidth() / Math.pow(2, zoom)),
                (int) (originalMap.getHeight() / Math.pow(2, zoom))
            );

            System.out.println("Rendering zoom level: " + zoom + " ...");
            renderer.renderPng(scaledMap, (int) (16 * Math.pow(2, zoom)), "zoom_" + zoom + ".png");
        }

        System.out.println("Done.");
    }

    /**
     * @return Class implementing generation algorithm to be used with the program
     */
    private DotMapGenerator getGenerator() {
        return new ExperimentGenerator();
//        return new SimpleGenerator();
    }

    /**
     * @return Class implementing scale algorithm to be used with the program
     */
    private DotMapScaler getScaler() {
        return new SampleAlgo();
    }
}
