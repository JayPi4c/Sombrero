package com.jaypi4c.mexicanhat.processing;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class InvertProcessor implements Processor {

    @Override
    public Image process(Image inputImage) {
        int width = (int) inputImage.getWidth();
        int height = (int) inputImage.getHeight();
        WritableImage outputImage = new WritableImage(width, height);
        var reader = inputImage.getPixelReader();
        var writer = outputImage.getPixelWriter();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color color = reader.getColor(x, y);
                // Example: Invert colors
                Color invertedColor = color.invert();
                writer.setColor(x, y, invertedColor);
            }
        }
        return outputImage;
    }
}
