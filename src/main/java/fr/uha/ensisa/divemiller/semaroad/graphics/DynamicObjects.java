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
        double time = (l - startTime) / 100000000.0;


    }

    @Override
    public void show(GraphicsContext gc) {
        TrafficLight lights = new TrafficLight(LightPosition.HORIZONTAL);
        lights.show(gc);
    }
}
