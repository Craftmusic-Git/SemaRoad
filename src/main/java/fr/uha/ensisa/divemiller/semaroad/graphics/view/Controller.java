package fr.uha.ensisa.divemiller.semaroad.graphics.view;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Controller {
    ArrayList<Car> cars;

    ArrayList<Point2D> TopWaitingPos;
    ArrayList<Point2D> RightWaitingPos;
    ArrayList<Point2D> BottomWaitingPos;
    ArrayList<Point2D> LeftWaitingPos;

    Point2D TopFinalPos;
    Point2D RightFinalPos;
    Point2D BottomFinalPos;
    Point2D LeftFinalPos;

    LinkedList<Car> topQueue;
    LinkedList<Car> rightQueue;
    LinkedList<Car> bottomQueue;
    LinkedList<Car> leftQueue;

    public Controller() {
        cars = new ArrayList<>();

        TopFinalPos = new Point2D(256,720);
        TopWaitingPos = new ArrayList<>();
        for (int i = 0; i < 4 ; i ++)
            TopWaitingPos.add(new Point2D(256,256 - i*64));

        RightFinalPos = new Point2D(720,256);
        RightWaitingPos = new ArrayList<>();
        for (int i = 0; i < 4 ; i ++)
            RightWaitingPos.add(new Point2D(384 + i*64,256));

        BottomFinalPos = new Point2D(320,-64);
        BottomWaitingPos = new ArrayList<>();
        for (int i = 0; i < 4 ; i ++)
            BottomWaitingPos.add(new Point2D(320,384 + i*64));

        LeftFinalPos = new Point2D(-64,256);
        LeftWaitingPos = new ArrayList<>();
        for (int i = 0; i < 4 ; i ++)
            LeftWaitingPos.add(new Point2D( 256 - i*64,256));

        topQueue = new LinkedList<>();
        rightQueue = new LinkedList<>();
        bottomQueue = new LinkedList<>();
        leftQueue = new LinkedList<>();
    }

    public void addCarInQueue(Car car) {
        cars.add(car);
        switch (car.lane) {
            case TOP -> {
                topQueue.add(car);
                car.setNextPosition(TopWaitingPos.get(topQueue.size()));
            }
            case RIGHT -> {
                rightQueue.add(car);
                car.setNextPosition(RightWaitingPos.get(rightQueue.size()));
            }
            case BOTTOM -> {
                bottomQueue.add(car);
                car.setNextPosition(BottomWaitingPos.get(bottomQueue.size()));
            }
            case LEFT -> {
                leftQueue.add(car);
                car.setNextPosition(LeftWaitingPos.get(leftQueue.size()));
            }
        }
    }

    public void traverser(Integer id) {
        Car car = cars.stream().filter(c -> c.getId().equals(id)).findFirst().get();
        car.setStatus(CarStatus.MIDDLE);
        switch (car.getLane()) {
            case TOP -> {
                topQueue.removeFirst();
            }
            case RIGHT -> {
                rightQueue.removeFirst();
            }
            case BOTTOM -> {
                bottomQueue.removeFirst();
            }
            case LEFT -> {
                leftQueue.removeFirst();
            }
        }
    }

    public void CalcNextPosition() {
        for (Car c : cars) {
            switch (c.getLane()) {
                case TOP -> {
                    switch (c.getStatus()) {
                        case MIDDLE -> {
                            c.setNextPosition(TopFinalPos);
                        }
                        case WAITING -> {
                            c.setNextPosition(TopWaitingPos.get(topQueue.size()));
                        }
                    }
                }
                case RIGHT -> {
                    switch (c.getStatus()) {
                        case MIDDLE -> {
                            c.setNextPosition(RightFinalPos);
                        }
                        case WAITING -> {
                            c.setNextPosition(RightWaitingPos.get(rightQueue.size()));
                        }
                    }
                }
                case BOTTOM -> {
                    switch (c.getStatus()) {
                        case MIDDLE -> {
                            c.setNextPosition(BottomFinalPos);
                        }
                        case WAITING -> {
                            c.setNextPosition(BottomWaitingPos.get(bottomQueue.size()));
                        }
                    }
                }
                case LEFT -> {
                    switch (c.getStatus()) {
                        case MIDDLE -> {
                            c.setNextPosition(LeftFinalPos);
                        }
                        case WAITING -> {
                            c.setNextPosition(LeftWaitingPos.get(leftQueue.size()));
                        }
                    }
                }
            }
        }
    }

    public ArrayList<Car> getCars() {
        return cars;
    }
}