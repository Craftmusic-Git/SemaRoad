package fr.uha.ensisa.divemiller.semaroad;

import fr.uha.ensisa.divemiller.semaroad.graphics.view.LanePosition;
import fr.uha.ensisa.divemiller.semaroad.graphics.view.LightPosition;
import fr.uha.ensisa.divemiller.semaroad.viewmodel.EventManager;
import fr.uha.ensisa.divemiller.semaroad.model.basicCarProblem.Car;
import fr.uha.ensisa.divemiller.semaroad.model.basicCarProblem.TrafficLight;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

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
        int j = 0;
            for (int i = 0; i < NBRE_CAR; i++) {
                Car car = new Car(j, LanePosition.LEFT, sf1, sf2, st1, st2);
                j++;
                car.start();
            }

            for (int i = 0; i < NBRE_CAR; i++) {
                Car car = new Car(j ,LanePosition.BOTTOM, sf1, sf2, st1, st2);
                j++;
                car.start();
            }

            for (int i = 0; i < NBRE_CAR; i++) {
                Car car = new Car(j ,LanePosition.RIGHT, sf1, sf2, st1, st2);
                j++;
                car.start();
            }

            for (int i = 0; i < NBRE_CAR; i++) {
                Car car = new Car(j ,LanePosition.TOP, sf1, sf2, st1, st2);
                j++;
                car.start();
            }
            Random rand = new Random();

        while (true) {
            ArrayList<fr.uha.ensisa.divemiller.semaroad.graphics.view.Car> topCar = em.getCars().stream().filter(car -> car.getLane().equals(LanePosition.TOP)).collect(Collectors.toCollection(ArrayList::new));
            ArrayList<fr.uha.ensisa.divemiller.semaroad.graphics.view.Car> rightCar = em.getCars().stream().filter(car -> car.getLane().equals(LanePosition.RIGHT)).collect(Collectors.toCollection(ArrayList::new));
            ArrayList<fr.uha.ensisa.divemiller.semaroad.graphics.view.Car> bottomCar = em.getCars().stream().filter(car -> car.getLane().equals(LanePosition.BOTTOM)).collect(Collectors.toCollection(ArrayList::new));
            ArrayList<fr.uha.ensisa.divemiller.semaroad.graphics.view.Car> leftCar = em.getCars().stream().filter(car -> car.getLane().equals(LanePosition.LEFT)).collect(Collectors.toCollection(ArrayList::new));

            Integer number = rand.nextInt(1,5);

            if (topCar.size() < 10 && number == 1) {
                Car car = new Car(j ,LanePosition.TOP, sf1, sf2, st1, st2);
                j++;
                car.start();
            }
            if (rightCar.size() < 10 && number == 2) {
                Car car = new Car(j ,LanePosition.RIGHT, sf1, sf2, st1, st2);
                j++;
                car.start();
            }
            if (bottomCar.size() < 10 && number == 3) {
                Car car = new Car(j ,LanePosition.BOTTOM, sf1, sf2, st1, st2);
                j++;
                car.start();
            }
            if (leftCar.size() < 10 && number == 4) {
                Car car = new Car(j ,LanePosition.LEFT, sf1, sf2, st1, st2);
                j++;
                car.start();
            }
            try {
                Thread.sleep(rand.nextInt(1000, 2000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
