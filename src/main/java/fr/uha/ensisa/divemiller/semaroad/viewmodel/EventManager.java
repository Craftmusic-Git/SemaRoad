package fr.uha.ensisa.divemiller.semaroad.viewmodel;

import fr.uha.ensisa.divemiller.semaroad.graphics.view.Car;
import fr.uha.ensisa.divemiller.semaroad.graphics.view.CarStatus;
import fr.uha.ensisa.divemiller.semaroad.graphics.view.LanePosition;
import fr.uha.ensisa.divemiller.semaroad.graphics.view.LightPosition;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class EventManager {

    private static EventManager em;

    private LightPosition lightPosition;

    private ArrayList<Car> cars;

    private EventManager() {
        cars = new ArrayList<>();
    }

    public static EventManager getEventManager() {
        if (em != null)
            return em;
        em = new EventManager();
        return em;
    }

    public void setLight(LightPosition lightPosition) {
        this.lightPosition = lightPosition;
    }

    public LightPosition getLightPosition() {
        return lightPosition;
    }

    public void addCar(Integer id, LanePosition position, Semaphore pret, Semaphore traverse) {
        CarFactory factory = new CarFactory();
        cars.add(factory.createCar(id, position, pret, traverse));
    }

    public void forwardIntersection(Integer id) {
        cars.stream().filter(car -> car.getId().equals(id)).findFirst().get().setStatus(CarStatus.MIDDLE);
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void resetEmCars() {
        cars = new ArrayList<>();
    }
}
