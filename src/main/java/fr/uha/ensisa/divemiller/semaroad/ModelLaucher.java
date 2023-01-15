package fr.uha.ensisa.divemiller.semaroad;

import fr.uha.ensisa.divemiller.semaroad.graphics.view.LanePosition;
import fr.uha.ensisa.divemiller.semaroad.graphics.view.LightPosition;
import fr.uha.ensisa.divemiller.semaroad.viewmodel.EventManager;
import fr.uha.ensisa.divemiller.semaroad.model.basicCarProblem.Car;
import fr.uha.ensisa.divemiller.semaroad.model.basicCarProblem.TrafficLight;

import java.util.concurrent.Semaphore;

public class ModelLaucher implements Runnable {

    Semaphore startedModel;

    private final Integer NBRE_CAR = 3;

    public ModelLaucher(Semaphore startedModel) {
        this.startedModel = startedModel;
    }

    @Override
    public void run() {
        EventManager em = EventManager.getEventManager();
        em.setLight(LightPosition.HORIZONTAL);

        startedModel.release();

        // Feu
        Semaphore sf1 = new Semaphore(1);
        Semaphore sf2 = new Semaphore(0);

        // voitures
        Semaphore st1 = new Semaphore(1);
        Semaphore st2 = new Semaphore(1);

        TrafficLight feu = new TrafficLight(sf1, sf2);
        feu.start();

        for (int i = 0; i < NBRE_CAR; i++) {
            Car car = new Car(i, LanePosition.LEFT, sf1, sf2, st1, st2);
            car.start();
        }

        for (int i = 0; i < 0; i++) {
            Car car = new Car(i + 5, LanePosition.BOTTOM, sf1, sf2, st1, st2);
            car.start();
        }
    }
}
