package fr.uha.ensisa.divemiller.semaroad.model.basicCarProblem;

import java.util.concurrent.Semaphore;

public class Car extends BasicCarProblem {

    protected Integer position;
    private Integer id;

    public Car(Integer id, Integer position, Semaphore sf1, Semaphore sf2, Semaphore st1, Semaphore st2) {

        this.id = id;
        this.position = position;

        this.sf1 = sf1;
        this.sf2 = sf2;

        this.st1 = st1;
        this.st2 = st2;

        if (this.position == 1) {
            BasicCarProblem.left += 1;
        } else if (this.position == 2) {
            BasicCarProblem.down += 1;
        }

    }

    @Override
    public void run() {
        try {

            sleep(rand.nextInt(9 * 1000));

            if (this.position == 1) {
                this.st1.acquire();
                this.sf1.acquire();

                this.avance();

                this.sf1.release();
                this.st1.release();
            }

            else if (this.position == 2) {

                this.st2.acquire();
                this.sf2.acquire();

                this.avance();

                this.sf2.release();
                this.st2.release();
            }
        }

        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void avance() {

        if (this.position == 1) {
            BasicCarProblem.left -= 1;
            System.out.println("BasicCarProblem.left = " + BasicCarProblem.left);
        }

        else if (this.position == 2) {
            BasicCarProblem.down -= 1;
            System.out.println("BasicCarProblem.down = " + BasicCarProblem.down);

        }

    }
}