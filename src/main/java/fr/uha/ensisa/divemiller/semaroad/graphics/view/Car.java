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

    private Boolean traverse;

    private Semaphore pret;

    public Car(Integer id, LanePosition lane, double angle, long x, long y, Semaphore pret) {
        position = new Point2D(x, y);
        this.lane = lane;
        this.angle = angle;

        this.id = id; // MICHEL !! les IDs c'est IMPORTANT !
        car = new Image("file:src/main/resources/fr/uha/ensisa/divemiller/semaroad/car.png");
        status = CarStatus.WAITING;

        this.pret = pret;
        this.traverse = false;
    }

    public void forward(double distance) {
        Boolean arrivedEndPosition = false;
        switch (status) {
            case WAITING:
                switch (lane) {
                    case LEFT:
                        if (position.getX() > nextPosition.getX()) {
                            if (nextPosition.getX() == 192) {
                                this.releaseArrivedEndPosition();
                            }
                            arrivedEndPosition = true;
                        }
                        break;
                    default:
                        break;
                }
                break;
            case MIDDLE:
                if (position.getX() >= 384) {
                    if (!arrivedEndPosition)
                        this.releaseArrivedEndPosition(); // Il faudrait utiliser un autre semaphore ici je pense
                    arrivedEndPosition = true;
                }
                break;

            default:
                break;
        }
        if (!arrivedEndPosition) {
            Point2D AB = nextPosition.subtract(position).normalize();
            position = new Point2D(position.getX() + distance * AB.getX(),
                    position.getY() + distance * AB.getY());

        }
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

    public void releaseArrivedEndPosition() {
        this.pret.release();
    }

    public void acquireArrivedEndPosition() {
        try {
            this.pret.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean aTraverse() {
        return this.traverse;
    }

    public void setTraverse(boolean traverse) {
        this.traverse = traverse;
    }
}
