package fr.uha.ensisa.divemiller.semaroad;

import fr.uha.ensisa.divemiller.semaroad.graphics.view.LightPosition;
import fr.uha.ensisa.divemiller.semaroad.viewmodel.EventManager;
import fr.uha.ensisa.divemiller.semaroad.model.basicCarProblem.TrafficLight;

import java.util.concurrent.Semaphore;

public class ModelLaucher implements Runnable {

    Semaphore startedModel;

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

        TrafficLight feu = new TrafficLight ( sf1, sf2);
        feu.start();
    }
}