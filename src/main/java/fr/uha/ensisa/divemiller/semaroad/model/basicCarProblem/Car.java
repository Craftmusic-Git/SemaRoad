package fr.uha.ensisa.divemiller.semaroad.model.basicCarProblem;

import java.util.concurrent.Semaphore;

import fr.uha.ensisa.divemiller.semaroad.graphics.view.LanePosition;
import fr.uha.ensisa.divemiller.semaroad.viewmodel.EventManager;

public class Car extends BasicCarProblem {

    protected LanePosition position;
    private Integer id;

    private Semaphore st;
    private Semaphore sf;

    private Semaphore pret;
    private Semaphore traverse;

    public Car(Integer id, LanePosition position, Semaphore sf1, Semaphore sf2, Semaphore st1, Semaphore st2) {

        this.id = id;
        this.position = position;

        this.pret = new Semaphore(0);
        this.traverse = new Semaphore(0);

        if (this.position.equals(LanePosition.LEFT) || this.position.equals(LanePosition.RIGHT)) {
            this.sf = sf1;
            this.st = st1;

        } else if (this.position.equals(LanePosition.BOTTOM) || this.position.equals(LanePosition.TOP)) {
            this.sf = sf2;
            this.st = st2;
        }
        EventManager.getEventManager().addCar(id, position, pret, traverse);
    }

    @Override
    public void run() {
        try {

            this.pret.acquire();
            System.out.println("Je suis arrivé à fin de mon attente !! ");

            this.st.acquire();
            this.sf.acquire();

            System.out.println("je passe le feu");
            this.avance();

            this.sf.release();
            this.st.release();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void avance() throws InterruptedException {
        EventManager.getEventManager().forwardIntersection(id);

        System.out.println("j'avance");
        this.traverse.acquire();
        System.out.println("arrive");
    }
}
