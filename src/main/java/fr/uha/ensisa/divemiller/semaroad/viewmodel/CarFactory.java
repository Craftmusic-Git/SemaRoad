package fr.uha.ensisa.divemiller.semaroad.viewmodel;

import java.util.concurrent.Semaphore;

import fr.uha.ensisa.divemiller.semaroad.graphics.view.Car;
import fr.uha.ensisa.divemiller.semaroad.graphics.view.LanePosition;

public class CarFactory {
    public Car createCar(Integer id, LanePosition lane, Semaphore pret) {
        Car rep = new Car(id, lane, 180, 256, 0, pret);
        switch (lane) {
            case TOP -> {
                rep = new Car(id, lane, 180, 256, -64, pret);
            }
            case RIGHT -> {
                rep = new Car(id, lane, 270, 704, 256, pret);
            }
            case BOTTOM -> {
                rep = new Car(id, lane, 0, 320, 704, pret);
            }
            case LEFT -> {
                rep = new Car(id, lane, 90, -64, 320, pret);
            }
        }
        return rep;
    }
}
