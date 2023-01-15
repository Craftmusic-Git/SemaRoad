package fr.uha.ensisa.divemiller.semaroad.model.basicCarProblem;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class BasicCarProblem extends Thread {

    protected final int WAIT = 5000;
    protected static Integer left = 0;
    protected static Integer bottom = 0;

    Random rand = new Random();

    // Feu
    protected Semaphore sf1;
    protected Semaphore sf2;

    // Traversée
    protected Semaphore st1;
    protected Semaphore st2;

}
