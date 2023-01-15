package fr.uha.ensisa.divemiller.semaroad.graphics.view;

import fr.uha.ensisa.divemiller.semaroad.graphics.core.DynamicGraphicObject;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import static fr.uha.ensisa.divemiller.semaroad.graphics.core.GraphicUtils.drawRotatedImage;

import java.util.concurrent.Semaphore;

public class Car implements DynamicGraphicObject {

    Integer id;
    Point2D position;
    double angle;
    LanePosition lane;
    Image car;
    CarStatus status;
    Point2D nextPosition;

    private Semaphore pret;

    public Car(Integer id, LanePosition lane) {
        position = new Point2D(256, 256);
        this.lane = lane;
        car = new Image("file:src/main/resources/fr/uha/ensisa/divemiller/semaroad/car.png");
        status = CarStatus.WAITING;
    }

    public Car(Integer id, LanePosition lane, double angle, long x, long y, Semaphore pret) {
        position = new Point2D(x, y);
        this.lane = lane;
        this.angle = angle;
        this.pret = pret;
        car = new Image("file:src/main/resources/fr/uha/ensisa/divemiller/semaroad/car.png");
        status = CarStatus.WAITING;
    }

    public void forward(double dist) {
        Point2D AB = nextPosition.subtract(position).normalize();
        position = new Point2D(position.getX() + dist * AB.getX(), position.getY() + dist * AB.getY());
    }

    @Override
    public void show(GraphicsContext gc) {
        drawRotatedImage(gc, car, angle, position.getX(), position.getY());
    }

    public Integer getId() {
        return id;
    }

    public void setStatus(CarStatus status) {
        this.status = status;
    }

    public LanePosition getLane() {
        return lane;
    }

    public void setLane(LanePosition lane) {
        this.lane = lane;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public CarStatus getStatus() {
        return status;
    }

    public Point2D getNextPosition() {
        return nextPosition;
    }

    public void setNextPosition(Point2D nextPosition) {
        this.nextPosition = nextPosition;
    }

    public void release() {
        this.pret.release();
    }

}
