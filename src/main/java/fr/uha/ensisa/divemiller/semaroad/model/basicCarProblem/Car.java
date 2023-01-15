package fr.uha.ensisa.divemiller.semaroad.model.basicCarProblem;

import java.util.concurrent.Semaphore;

import fr.uha.ensisa.divemiller.semaroad.graphics.view.LanePosition;

public class Car extends BasicCarProblem {

    protected LanePosition position;
    private Integer id;

    private Semaphore st;
    private Semaphore sf;

    public Car(Integer id, LanePosition position, Semaphore sf1, Semaphore sf2, Semaphore st1, Semaphore st2) {

        this.id = id;
        this.position = position;

        if (this.position.equals(LanePosition.LEFT)) {
            BasicCarProblem.left += 1;
            this.sf = sf1;
            this.st = st1;

        } else if (this.position.equals(LanePosition.BOTTOM)) {
            BasicCarProblem.bottom += 1;
            this.sf = sf2;
            this.st = st2;
        }

    }

    @Override
    public void run() {
        try {

            sleep(rand.nextInt(9 * 1000));

            this.st.acquire();
            this.sf.acquire();

            this.avance();

            this.sf.release();
            this.st.release();
        }

        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void avance() {

        if (this.position.equals(LanePosition.LEFT)) {
            BasicCarProblem.left -= 1;
            System.out.println("BasicCarProblem.left = " + BasicCarProblem.left);
        }

        else if (this.position.equals(LanePosition.BOTTOM)) {
            BasicCarProblem.bottom -= 1;
            System.out.println("BasicCarProblem.down = " + BasicCarProblem.bottom);

        }

    }
}