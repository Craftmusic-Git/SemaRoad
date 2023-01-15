package fr.uha.ensisa.divemiller.semaroad;

import fr.uha.ensisa.divemiller.semaroad.graphics.view.LightPosition;
import fr.uha.ensisa.divemiller.semaroad.viewmodel.EventManager;

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
        while (true) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if (em.getLightPosition() == LightPosition.HORIZONTAL) em.setLight(LightPosition.VERTICAL);
            else em.setLight(LightPosition.HORIZONTAL);
        }
    }
}
