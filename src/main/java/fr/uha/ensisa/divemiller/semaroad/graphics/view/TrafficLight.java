package fr.uha.ensisa.divemiller.semaroad.graphics.view;

import fr.uha.ensisa.divemiller.semaroad.graphics.core.DynamicGraphicObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static fr.uha.ensisa.divemiller.semaroad.graphics.core.GraphicUtils.drawRotatedImage;

public class TrafficLight implements DynamicGraphicObject {
    Image green;
    Image red;
    LightPosition lightPosition;

    public TrafficLight(LightPosition lightPosition) {
        green = new Image("file:src/main/resources/fr/uha/ensisa/divemiller/semaroad/green.jpg");
        red = new Image("file:src/main/resources/fr/uha/ensisa/divemiller/semaroad/red.jpg");
        this.lightPosition = lightPosition;
    }

    public void switchLightPosition() {
        if (lightPosition == LightPosition.HORIZONTAL) lightPosition = LightPosition.VERTICAL;
        else lightPosition = LightPosition.HORIZONTAL;
    }


    @Override
    public void show(GraphicsContext gc) {
        switch (lightPosition) {
            case HORIZONTAL -> {
                drawRotatedImage(gc,green, 90, 192, 384);
                drawRotatedImage(gc,green, 270, 384, 192);
                drawRotatedImage(gc, red, 180, 192,192);
                drawRotatedImage(gc, red, 0, 384,384);
            }
            case VERTICAL -> {
                drawRotatedImage(gc,red, 90, 192, 384);
                drawRotatedImage(gc,red, 270, 384, 192);
                drawRotatedImage(gc, green, 180, 192,192);
                drawRotatedImage(gc, green, 0, 384,384);
            }
        }
    }
}
