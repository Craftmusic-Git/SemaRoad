package fr.uha.ensisa.divemiller.semaroad.graphics;

import fr.uha.ensisa.divemiller.semaroad.graphics.core.DynamicGraphicObject;
import fr.uha.ensisa.divemiller.semaroad.graphics.view.*;
import fr.uha.ensisa.divemiller.semaroad.viewmodel.EventManager;
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;

public class DynamicObjects extends AnimationTimer implements DynamicGraphicObject {

    GraphicsContext gc;
    long startTime;
    Controller controller;
    Boolean firstGet;

    public DynamicObjects(GraphicsContext gc) {
        this.gc = gc;
        startTime = System.nanoTime();
        controller = new Controller();
        this.firstGet = false;
    }

    @Override
    public void handle(long l) {
        Roads layout = new Roads();
        layout.show(gc);

        double time = (l - startTime) / 1000000.0;
        Double vitesse = .128 * time;

        EventManager em = EventManager.getEventManager();
        TrafficLight tl = new TrafficLight(em.getLightPosition());
        tl.show(gc);

        if (!this.firstGet) {
            em.getCars().stream().forEach(c -> controller.addCarInQueue(c));
            this.firstGet = true;
        }

        controller.CalcNextPosition();
        controller.getCars().forEach(c -> {
            if (!c.aTraverse() && c.getStatus().equals(CarStatus.MIDDLE)) {
                controller.traverser(c.getId());
                c.setTraverse(true);
            }
            c.forward(vitesse);
            c.show(gc);
        });

        this.startTime = System.nanoTime();
    }

    @Override
    public void show(GraphicsContext gc) {
        TrafficLight lights = new TrafficLight(LightPosition.HORIZONTAL);
        lights.show(gc);
    }

}
