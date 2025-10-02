package com.jaypi4c.mexicanhat.processing;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class BrightnessProcessor implements Processor {


    @Override
    public Image process(Image inputImage) {
        int width = (int) inputImage.getWidth();
        int height = (int) inputImage.getHeight();
        WritableImage outputImage = new WritableImage(width, height);
        var reader = inputImage.getPixelReader();
        var writer = outputImage.getPixelWriter();

        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                writer.setColor(x, y, (reader.getColor(x, y).getBrightness() < .2 ? Color.PINK : reader.getColor(x, y)));
            }
        }

        return outputImage;
    }


}
