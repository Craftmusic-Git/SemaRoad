package fr.uha.ensisa.divemiller.semaroad.graphics.view;

import fr.uha.ensisa.divemiller.semaroad.graphics.core.DynamicGraphicObject;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static fr.uha.ensisa.divemiller.semaroad.graphics.core.GraphicUtils.drawRotatedImage;

public class Car implements DynamicGraphicObject {

    Point2D position;

    Image car;

    public Car() {
        position = new Point2D(256,256);
        car = new Image("file:src/main/resources/fr/uha/ensisa/divemiller/semaroad/car.png");
    }

    public Car(long x, long y) {
        position = new Point2D(x,y);
        car = new Image("file:src/main/resources/fr/uha/ensisa/divemiller/semaroad/car.png");
    }

    @Override
    public void show(GraphicsContext gc) {
        drawRotatedImage(gc, car, 270, position.getX(), position.getY());
    }
}
