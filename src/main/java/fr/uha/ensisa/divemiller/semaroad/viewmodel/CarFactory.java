package fr.uha.ensisa.divemiller.semaroad.viewmodel;

import fr.uha.ensisa.divemiller.semaroad.graphics.view.Car;
import fr.uha.ensisa.divemiller.semaroad.graphics.view.LanePosition;

public class CarFactory {
    public Car createCar(Integer id, LanePosition lane) {
        Car rep = new Car(id, lane, 180, 256, 0);
        switch (lane) {
            case TOP -> {
                rep = new Car(id, lane, 180, 256, -64);
            }
            case RIGHT -> {
                rep = new Car(id, lane, 270, 704, 256);
            }
            case BOTTOM -> {
                rep = new Car(id, lane, 0, 320, 704);
            }
            case LEFT -> {
                rep = new Car(id, lane, 90, -64, 320);
            }
        }
        return rep;
    }
}
