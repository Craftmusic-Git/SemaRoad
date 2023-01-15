package fr.uha.ensisa.divemiller.semaroad.layout;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TrafficLight implements GraphicObject {
    Image green;
    Image red;
    LightPosition lightPosition;

    public TrafficLight(LightPosition lightPosition) {
        green = new Image("file:src/main/resources/fr/uha/ensisa/divemiller/semaroad/green.jpg");
        red = new Image("file:src/main/resources/fr/uha/ensisa/divemiller/semaroad/red.jpg");
        this.lightPosition = lightPosition;
    }

    public void switchLightPosition() {
        if (lightPosition == LightPosition.HORIZONTAL) lightPosition = LightPosition.VERTICAL;
        else lightPosition = LightPosition.HORIZONTAL;
    }

    public Group VerticalLane() {
        Group rep = new Group();
        switch (lightPosition) {
            case HORIZONTAL -> {
                ImageView lightTop = new ImageView(red);
                lightTop.setX(192);
                lightTop.setY(192);
                lightTop.setRotate(180);
                rep.getChildren().add(lightTop);
                ImageView lightBottom = new ImageView(red);
                lightBottom.setX(384);
                lightBottom.setY(384);
                rep.getChildren().add(lightBottom);
            }
            case VERTICAL -> {
                ImageView lightTop = new ImageView(green);
                lightTop.setX(192);
                lightTop.setY(192);
                lightTop.setRotate(180);
                rep.getChildren().add(lightTop);
                ImageView lightBottom = new ImageView(green);
                lightBottom.setX(384);
                lightBottom.setY(384);
                rep.getChildren().add(lightBottom);
            }
        }
        return rep;
    }

    public Group HorizontalGroup() {
        Group rep = new Group();
        switch (lightPosition) {
            case HORIZONTAL -> {
                ImageView lightTop = new ImageView(green);
                lightTop.setX(192);
                lightTop.setY(384);
                lightTop.setRotate(90);
                rep.getChildren().add(lightTop);
                ImageView lightBottom = new ImageView(green);
                lightBottom.setX(384);
                lightBottom.setY(192);
                lightBottom.setRotate(270);
                rep.getChildren().add(lightBottom);
            }
            case VERTICAL -> {
                ImageView lightTop = new ImageView(red);
                lightTop.setX(192);
                lightTop.setY(384);
                lightTop.setRotate(90);
                rep.getChildren().add(lightTop);
                ImageView lightBottom = new ImageView(red);
                lightBottom.setX(192);
                lightBottom.setY(384);
                lightBottom.setRotate(270);
                rep.getChildren().add(lightBottom);
            }
        }
        return rep;
    }



    @Override
    public Group show() {
        Group rep = new Group();
        rep.getChildren().add(VerticalLane());
        rep.getChildren().add(HorizontalGroup());
        return rep;
    }
}
