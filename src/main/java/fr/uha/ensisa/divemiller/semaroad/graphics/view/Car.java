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
    Boolean added;

    private Boolean hasCrossed;

    private Semaphore pret;
    private Semaphore traverse;

    public Car(Integer id, LanePosition lane, double angle, long x, long y, Semaphore pret, Semaphore traverse) {
        position = new Point2D(x, y);
        this.lane = lane;
        this.angle = angle;
        this.added = false;

        this.id = id; // MICHEL !! les IDs c'est IMPORTANT !
        car = new Image("file:src/main/resources/fr/uha/ensisa/divemiller/semaroad/car.png");
        status = CarStatus.WAITING;

        this.pret = pret;
        this.traverse = traverse;
        this.hasCrossed = false;
    }

    public void forward(double distance) {
        Boolean arrivedEndPosition = false;

        Integer beginLeftSquarePositionX = 192;
        Integer endLeftSquarePositionX = 384;

        Integer beginBottomSquarePositionY = 384;
        Integer endBottomSquarePositionY = 190;

        Integer beginRightSquarePositionX = 384;
        Integer endRightSquarePositionX = 192;

        Integer beginTopSquarePositionY = 190;
        Integer endTopSquarePositionY = 384;


        switch (status) {
            case WAITING:
                switch (lane) {
                    case TOP:
                        if (position.getY() >= nextPosition.getY()) {
                            if (nextPosition.getY() >= beginTopSquarePositionY) {
                                this.releaseArrivedEndPosition();
                            }
                            arrivedEndPosition = true;
                        }
                        break;

                    case RIGHT:
                        if (position.getX() <= nextPosition.getX()) {
                            if (nextPosition.getX() <= beginRightSquarePositionX) {
                                this.releaseArrivedEndPosition();
                            }
                            arrivedEndPosition = true;
                        }
                        break;

                    case LEFT:
                        if (position.getX() >= nextPosition.getX()) {
                            if (nextPosition.getX() >= beginLeftSquarePositionX) {
                                this.releaseArrivedEndPosition();
                            }
                            arrivedEndPosition = true;
                        }
                        break;

                    case BOTTOM:
                        if (position.getY() <= nextPosition.getY()) {
                            if (nextPosition.getY() <= beginBottomSquarePositionY) {
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
                switch (lane) {
                    case TOP:
                        if (position.getY() >= endTopSquarePositionY) {
                            if (!arrivedEndPosition)
                                this.releaseTraverse();
                            arrivedEndPosition = false;
                        }
                        break;
                    case RIGHT:
                        if (position.getX() <= endRightSquarePositionX) {
                            if (!arrivedEndPosition)
                                this.releaseTraverse();
                            arrivedEndPosition = false;
                        }
                        break;
                    case LEFT:
                        if (position.getX() >= endLeftSquarePositionX) {
                            if (!arrivedEndPosition)
                                this.releaseTraverse();
                            arrivedEndPosition = false;
                        }
                        break;
                    case BOTTOM:
                        if (position.getY() <= endBottomSquarePositionY) {
                            if (!arrivedEndPosition)
                                this.releaseTraverse();
                            arrivedEndPosition = false;
                        }
                        break;
                    default:
                        break;
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

    public void releaseTraverse() {
        this.traverse.release();
    }

    public boolean hasCrossed() {
        return this.hasCrossed;
    }

    public void setCrossed(boolean traverse) {
        this.hasCrossed = traverse;
    }

    public Boolean getAdded() {
        return added;
    }

    public void setAdded(Boolean added) {
        this.added = added;
    }
}
