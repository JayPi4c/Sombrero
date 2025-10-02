package com.jaypi4c.mexicanhat;


import com.jaypi4c.mexicanhat.processing.EdgeDetectionProcessor;
import com.jaypi4c.mexicanhat.processing.Processor;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class Controller {

    private final Processor processor = new EdgeDetectionProcessor();

    @FXML
    private Canvas drawingCanvas;
    @FXML
    private AnchorPane drawingAnchorPane;
    @FXML
    private ImageView processedImageView;

    @FXML
    public void initialize() {
        // Bind canvas size to AnchorPane size
        drawingCanvas.widthProperty().bind(drawingAnchorPane.widthProperty());
        drawingCanvas.heightProperty().bind(drawingAnchorPane.heightProperty());


        GraphicsContext gc = drawingCanvas.getGraphicsContext2D();
        // Set the canvas background to white
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, drawingCanvas.getWidth(), drawingCanvas.getHeight());

        // Set up mouse event handlers for drawing
        drawingCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            gc.beginPath();
            gc.moveTo(event.getX(), event.getY());
            gc.stroke();
        });

        drawingCanvas.addEventHandler(MouseEvent.MOUSE_DRAGGED, event -> {
            gc.lineTo(event.getX(), event.getY());
            gc.stroke();
        });

        drawingCanvas.addEventHandler(MouseEvent.MOUSE_RELEASED, _ -> updateProcessedImage());

        // Set stroke color to black
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(8.0);
        updateProcessedImage();
    }


    private void updateProcessedImage() {
        WritableImage snapshot = drawingCanvas.snapshot(null, null);
        Image processedImage = processor.process(snapshot);
        processedImageView.setImage(processedImage);
    }


}
