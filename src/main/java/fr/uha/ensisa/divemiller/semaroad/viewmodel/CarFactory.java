package fr.uha.ensisa.divemiller.semaroad.viewmodel;

import java.util.concurrent.Semaphore;

import fr.uha.ensisa.divemiller.semaroad.graphics.view.Car;
import fr.uha.ensisa.divemiller.semaroad.graphics.view.LanePosition;

public class CarFactory {
    public Car createCar(Integer id, LanePosition lane, Semaphore pret, Semaphore traverse) {
        Car rep = new Car(id, lane, 180, 256, 0, pret, traverse);
        switch (lane) {
            case TOP -> {
                rep = new Car(id, lane, 180, 256, -64, pret, traverse);
            }
            case RIGHT -> {
                rep = new Car(id, lane, 270, 704, 256, pret, traverse);
            }
            case BOTTOM -> {
                rep = new Car(id, lane, 0, 320, 704, pret, traverse);
            }
            case LEFT -> {
                rep = new Car(id, lane, 90, -64, 320, pret, traverse);
            }
        }
        return rep;
    }
}
