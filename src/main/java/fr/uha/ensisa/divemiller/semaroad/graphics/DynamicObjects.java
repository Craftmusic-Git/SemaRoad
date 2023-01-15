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

        em.getCars().stream().forEach(c -> {
            if (!c.getAdded()) {
                controller.addCarInQueue(c);
                c.setAdded(true);
            }
        });

        controller.CalcNextPosition();
        controller.getCars().forEach(c -> {
            if (c.getPosition().getX() > 1024 || c.getPosition().getX() < -200 || c.getPosition().getY() > 1024 || c.getPosition().getY() < -200) {
                em.getCars().remove(c);
                controller.getCars().remove(c);
            }
            if (!c.hasCrossed() && c.getStatus().equals(CarStatus.MIDDLE)) {
                controller.traverser(c.getId());
                c.setCrossed(true);
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
