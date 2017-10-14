package nl.tue.algogeo.renderer;

import nl.tue.algogeo.Color;
import nl.tue.algogeo.Dot;
import nl.tue.algogeo.DotMap;
import nl.tue.algogeo.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DotMapRenderer {

    private BufferedImage image;
    private DotMap map;
    private int pixelsPerDot;

    public void renderPng(DotMap map, int pixelsPerDot, String filename) throws IOException {
        this.image = new BufferedImage(map.getWidth() * pixelsPerDot, map.getHeight() * pixelsPerDot, BufferedImage.TYPE_INT_RGB);
        this.map = map;
        this.pixelsPerDot = pixelsPerDot;

        for (int mapY = 0; mapY < map.getHeight(); mapY++) {
            for (int mapX = 0; mapX < map.getWidth(); mapX++) {
                Dot dot = map.get(mapX, mapY);
                if (dot != null) {
                    drawDot(mapX, mapY, dot);
                } else {
                    drawEmpty(mapX, mapY);
                }
            }
        }

        File outputFile = new File(filename);
        ImageIO.write(image, "png", outputFile);
    }

    private void drawDot(int mapX, int mapY, Dot dot) {
        Color color = dot.getLabel().getColor();

        double dotCenterX = mapX * pixelsPerDot + .5 * pixelsPerDot -.5;
        double dotCenterY = mapY * pixelsPerDot + .5 * pixelsPerDot -.5;

        for (int y = mapY * pixelsPerDot; y < (mapY + 1) * pixelsPerDot; y++) {
            for (int x = mapX * pixelsPerDot; x < (mapX + 1) * pixelsPerDot; x++) {
                double relX = x - dotCenterX;
                double relY = y - dotCenterY;
                double dist = Math.sqrt(relX * relX + relY * relY);
                Color pixelColor = Utils.merge(color, Color.WHITE, (float) (dist - pixelsPerDot/2 + 1));
                image.setRGB(x, y, Utils.colorToInt(
                    Utils.gamma(pixelColor.r, 2.2f),
                    Utils.gamma(pixelColor.g, 2.2f),
                    Utils.gamma(pixelColor.b, 2.2f)
                ));
            }
        }
    }

    private void drawEmpty(int mapX, int mapY) {
        Color grey = new Color(0.8f, 0.8f, 0.8f);

        double dotCenterX = mapX * pixelsPerDot + .5 * pixelsPerDot -.5;
        double dotCenterY = mapY * pixelsPerDot + .5 * pixelsPerDot -.5;

        for (int y = mapY * pixelsPerDot; y < (mapY + 1) * pixelsPerDot; y++) {
            for (int x = mapX * pixelsPerDot; x < (mapX + 1) * pixelsPerDot; x++) {
                double relX = x - dotCenterX;
                double relY = y - dotCenterY;
                double dist = Math.sqrt(relX * relX + relY * relY);
                Color pixelColor = Utils.merge(grey, Color.WHITE, (float) (dist - pixelsPerDot/8 + 1));
                image.setRGB(x, y, Utils.colorToInt(
                    Utils.gamma(pixelColor.r, 2.2f),
                    Utils.gamma(pixelColor.g, 2.2f),
                    Utils.gamma(pixelColor.b, 2.2f)
                ));
            }
        }
    }
}