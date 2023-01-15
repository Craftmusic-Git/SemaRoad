package fr.uha.ensisa.divemiller.semaroad.graphics;

import fr.uha.ensisa.divemiller.semaroad.graphics.core.DynamicGraphicObject;
import fr.uha.ensisa.divemiller.semaroad.graphics.view.*;
import fr.uha.ensisa.divemiller.semaroad.viewmodel.EventManager;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class DynamicObjects extends AnimationTimer implements DynamicGraphicObject {

    GraphicsContext gc;
    long startTime;
    Controller controller;

    public DynamicObjects(GraphicsContext gc) {
        this.gc = gc;
        startTime = System.nanoTime();
        controller = new Controller();
    }

    @Override
    public void handle(long l) {
        Roads layout = new Roads();
        layout.show(gc);
        double time = (l - startTime) / 100000000.0;

        EventManager em = EventManager.getEventManager();
        TrafficLight tl = new TrafficLight(em.getLightPosition());
        tl.show(gc);

        em.getCars().stream().forEach(c -> controller.addCarInQueue(c));
        em.resetEmCars();

        controller.CalcNextPosition();


        //car.forward(.128 * time);
        controller.getCars().forEach(c -> {
            c.forward(.128 * time);
            c.show(gc);
        });
    }

    @Override
    public void show(GraphicsContext gc) {
        TrafficLight lights = new TrafficLight(LightPosition.HORIZONTAL);
        lights.show(gc);
    }


}
