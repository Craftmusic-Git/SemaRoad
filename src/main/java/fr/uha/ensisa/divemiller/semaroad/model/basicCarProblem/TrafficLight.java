package fr.uha.ensisa.divemiller.semaroad.model.basicCarProblem;

import java.util.concurrent.Semaphore;

import fr.uha.ensisa.divemiller.semaroad.graphics.view.LightPosition;
import fr.uha.ensisa.divemiller.semaroad.viewmodel.EventManager;

public class TrafficLight extends BasicCarProblem {

    private Integer feu;

    public TrafficLight(Semaphore sf1, Semaphore sf2) {

        this.sf1 = sf1;
        this.sf2 = sf2;

    }

    @Override
    public void run() {

        this.feu = 1;

        while (true) {

            try {
                sleep(this.WAIT);

                if (feu == 1) {

                    this.sf1.acquire();
                    this.sf2.release();

                    System.out.println("Changement de feu = 1 -> 2");

                    EventManager.getEventManager().setLight(LightPosition.HORIZONTAL);

                    this.feu = 2;

                } else if (feu == 2) {

                    this.sf2.acquire();
                    this.sf1.release();

                    System.out.println("Changement de feu = 2 -> 1");
                    EventManager.getEventManager().setLight(LightPosition.VERTICAL);
                    this.feu = 1;

                }

            }

            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
