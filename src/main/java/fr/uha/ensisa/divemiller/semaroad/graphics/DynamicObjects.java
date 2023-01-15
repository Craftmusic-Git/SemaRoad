package fr.uha.ensisa.divemiller.semaroad.graphics;

import fr.uha.ensisa.divemiller.semaroad.graphics.core.DynamicGraphicObject;
import fr.uha.ensisa.divemiller.semaroad.graphics.view.Car;
import fr.uha.ensisa.divemiller.semaroad.graphics.view.LightPosition;
import fr.uha.ensisa.divemiller.semaroad.graphics.view.Roads;
import fr.uha.ensisa.divemiller.semaroad.graphics.view.TrafficLight;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class DynamicObjects extends AnimationTimer implements DynamicGraphicObject {

    GraphicsContext gc;
    long startTime;

    public DynamicObjects(GraphicsContext gc) {
        this.gc = gc;
        startTime = System.nanoTime();
    }

    @Override
    public void handle(long l) {
        Roads layout = new Roads();
        layout.show(gc);
        double time = (l - startTime) / 50000000.0;

        Car car = new Car(640 - (long) time, 256);
        Car car2 = new Car(256, ((long) (2 * time)));
        car2.show(gc);
        car.show(gc);
    }

    @Override
    public void show(GraphicsContext gc) {
        TrafficLight lights = new TrafficLight(LightPosition.HORIZONTAL);
        lights.show(gc);
    }
}
