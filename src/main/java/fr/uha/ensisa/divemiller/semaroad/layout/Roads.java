package fr.uha.ensisa.divemiller.semaroad.layout;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Roads implements GraphicObject {

    Image road;

    Image roadCorner;

    Image ground;

    public Roads() {
        ground = new Image("file:src/main/resources/fr/uha/ensisa/divemiller/semaroad/ground.jpg");
        road = new Image("file:src/main/resources/fr/uha/ensisa/divemiller/semaroad/road.jpg");
        roadCorner = new Image("file:src/main/resources/fr/uha/ensisa/divemiller/semaroad/road2.jpg");
    }

    public Group show() {
        ArrayList<ImageView> grounds = new ArrayList<>();

        for (double i = 0; i < 10 ; i++) {
            for (double j = 0; j < 10 ; j++ ) {
                ImageView im = new ImageView(ground);
                im.setX(i * 64);
                im.setY(j * 64);
                im.setPreserveRatio(false);
                grounds.add(im);
            }
        }

        // Top
        for (double i = 0; i < 4; i++) {
            ImageView imR = new ImageView(road);
            imR.setX(256);
            imR.setY(i * 64);

            ImageView imL = new ImageView(road);
            imL.setX(320);
            imL.setY(i * 64);
            imL.setRotate(180);

            grounds.add(imR);
            grounds.add(imL);
        }
        ImageView iCT = new ImageView(roadCorner);
        iCT.setX(256);
        iCT.setY(256);
        grounds.add(iCT);

        // Bottom
        for (double i = 0; i < 4; i++) {
            ImageView imR = new ImageView(road);
            imR.setX(256);
            imR.setY(i * 64 + 384);

            ImageView imL = new ImageView(road);
            imL.setX(320);
            imL.setY(i * 64 + 384);
            imL.setRotate(180);

            grounds.add(imR);
            grounds.add(imL);
        }
        ImageView iCB = new ImageView(roadCorner);
        iCB.setX(320);
        iCB.setY(320);
        iCB.setRotate(180);
        grounds.add(iCB);

        // Left
        for (double i = 0; i < 4; i++) {
            ImageView imR = new ImageView(road);
            imR.setY(256);
            imR.setX(i * 64);
            imR.setRotate(90);

            ImageView imL = new ImageView(road);
            imL.setY(320);
            imL.setX(i * 64);
            imL.setRotate(270);

            grounds.add(imR);
            grounds.add(imL);
        }
        ImageView iCL = new ImageView(roadCorner);
        iCL.setX(320);
        iCL.setY(256);
        iCL.setRotate(90);
        grounds.add(iCL);

        // Right
        for (double i = 0; i < 4; i++) {
            ImageView imR = new ImageView(road);
            imR.setY(256);
            imR.setX(i * 64 + 384);
            imR.setRotate(90);

            ImageView imL = new ImageView(road);
            imL.setY(320);
            imL.setX(i * 64 + 384);
            imL.setRotate(270);

            grounds.add(imR);
            grounds.add(imL);
        }
        ImageView iCR = new ImageView(roadCorner);
        iCR.setX(256);
        iCR.setY(320);
        iCR.setRotate(270);
        grounds.add(iCR);

        Group rep = new Group();
        rep.getChildren().addAll(grounds);

        return rep;
    }
}
