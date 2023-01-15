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
    ArrayList<CarLane> lanes;

    public DynamicObjects(GraphicsContext gc) {
        this.gc = gc;
        startTime = System.nanoTime();
        lanes = new ArrayList<>();

        lanes.add(new CarLane(LanePosition.TOP));
        lanes.add(new CarLane(LanePosition.RIGHT));
        lanes.add(new CarLane(LanePosition.BOTTOM));
        lanes.add(new CarLane(LanePosition.LEFT));
    }

    @Override
    public void handle(long l) {
        Roads layout = new Roads();
        layout.show(gc);
        double time = (l - startTime) / 100000000.0;

        EventManager em = EventManager.getEventManager();
        TrafficLight tl = new TrafficLight(em.getLightPosition());
        tl.show(gc);

        for (Car c : em.getCars()) {
            switch (c.getLane()) {
                case TOP -> {
                    lanes.get(0).addCar(c);
                }
                case RIGHT -> {
                    lanes.get(1).addCar(c);
                }
                case BOTTOM -> {
                    lanes.get(2).addCar(c);
                }
                case LEFT -> {
                    lanes.get(3).addCar(c);
                }
            }
        }
        em.resetEmCars();

        lanes.stream().forEach(carLane -> carLane.getCars().forEach(car -> {
            if (car.getStatus() == CarStatus.FORWARD || car.getStatus() == CarStatus.MIDDLE)
                car.forward(.128 * time);
            car.show(gc);
        }));
    }

    @Override
    public void show(GraphicsContext gc) {
        TrafficLight lights = new TrafficLight(LightPosition.HORIZONTAL);
        lights.show(gc);
    }


}
