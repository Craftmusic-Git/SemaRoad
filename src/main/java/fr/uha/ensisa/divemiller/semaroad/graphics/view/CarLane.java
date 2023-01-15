package fr.uha.ensisa.divemiller.semaroad.graphics.view;

import fr.uha.ensisa.divemiller.semaroad.graphics.core.DynamicGraphicObject;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.LinkedList;

public class CarLane implements DynamicGraphicObject {

    LanePosition lane;
    ArrayList<Car> cars;
    LinkedList<Car> queue;
    ArrayList<Point2D> places;
    Point2D objective;
    Car movingCar;
    public CarLane(LanePosition lane) {
        this.lane = lane;
        cars = new ArrayList<>();
        queue = new LinkedList<>();
        places = new ArrayList<>();
        switch (lane) {
            case TOP -> {
                objective = new Point2D(256,720);
                for (int i = 0; i < 8 ; i ++)
                    places.add(new Point2D(256,256 - i*64));
            }
            case RIGHT -> {
                objective = new Point2D(720,256);
                for (int i = 0; i < 8 ; i ++)
                    places.add(new Point2D(384 + i*64,256));
            }
            case BOTTOM -> {
                objective = new Point2D(320,-64);
                for (int i = 0; i < 8 ; i ++)
                    places.add(new Point2D(320,384 + i*64));
            }
            case LEFT -> {
                objective = new Point2D(-64,256);
                for (int i = 0; i < 8 ; i ++)
                    places.add(new Point2D( 256 - i*64,256));
            }
        }
   }

   public void calcNextPosition() {

   }

    @Override
    public void show(GraphicsContext gc) {
       for (Car car : cars) {
           car.show(gc);
       }
    }

    public void addCar(Car car) {
        queue.add(car);
        cars.add(car);
        car.setStatus(CarStatus.WAITING);
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

}
