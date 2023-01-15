package fr.uha.ensisa.divemiller.semaroad.model.basicCarProblem;

import java.util.concurrent.Semaphore;

import fr.uha.ensisa.divemiller.semaroad.graphics.view.LightPosition;
import fr.uha.ensisa.divemiller.semaroad.viewmodel.EventManager;

public class TrafficLight extends BasicCarProblem {

    private LightPosition feu;

    public TrafficLight(Semaphore sf1, Semaphore sf2) {

        this.sf1 = sf1;
        this.sf2 = sf2;

    }

    @Override
    public void run() {

        this.feu = LightPosition.HORIZONTAL;
        while (true) {

            try {

                sleep(WAIT);

                if (feu.equals(LightPosition.HORIZONTAL)) {

                    this.sf1.acquire();
                    this.sf2.release();

                    System.out.println("Changement de feu = HORIZONTAL -> VERTICAL");

                    EventManager.getEventManager().setLight(LightPosition.VERTICAL);

                    this.feu = LightPosition.VERTICAL;

                } else if (feu.equals(LightPosition.VERTICAL)) {

                    this.sf2.acquire();
                    this.sf1.release();

                    System.out.println("Changement de feu = VERTICAL -> HORIZONTAL");
                    EventManager.getEventManager().setLight(LightPosition.HORIZONTAL);
                    this.feu = LightPosition.HORIZONTAL;

                }

            }

            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
