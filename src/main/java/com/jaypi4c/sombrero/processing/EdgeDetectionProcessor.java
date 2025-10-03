package com.jaypi4c.sombrero.processing;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class EdgeDetectionProcessor implements Processor {

    private final double THRESHOLD = 0.2d; // Threshold for edge detection


    private final int[][] FILTER = {
            {-1, -1, -1},
            {-1, 8, -1},
            {-1, -1, -1}
    };

    public Image process(Image inputImage) {
        int width = (int) inputImage.getWidth();
        int height = (int) inputImage.getHeight();
        WritableImage outputImage = new WritableImage(width, height);
        var reader = inputImage.getPixelReader();
        var writer = outputImage.getPixelWriter();

        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                float energy = 0;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Color color = reader.getColor(x + i - 1, y + j - 1);
                        double brightness = color.getBrightness();
                        energy += brightness < THRESHOLD ? FILTER[i][j] : 0;
                    }
                }

                // threshold
                if (energy > 0.5) {
                    writer.setColor(x, y, Color.BLACK);
                } else {
                    // set to white if below threshold (ie within black area)
                    // if (reader.getColor(x, y).getBrightness() < THRESHOLD) {
                    //    writer.setColor(x, y, Color.WHITE);
                    //} else {
                    //    writer.setColor(x, y, reader.getColor(x, y));
                    //}
                    writer.setColor(x, y, Color.WHITE);
                }
            }
        }
        return outputImage;
    }


}
