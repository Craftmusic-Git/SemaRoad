package fr.uha.ensisa.divemiller.semaroad.layout;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Roads {

    Image road;

    Image roadCorner;

    Image ground;

    public Roads() {
        ground = new Image("file:src/main/resources/fr/uha/ensisa/divemiller/semaroad/ground.jpg");
        road = new Image("file:src/main/resources/fr/uha/ensisa/divemiller/semaroad/road.jpg");
        roadCorner = new Image("file:src/main/resources/fr/uha/ensisa/divemiller/semaroad/road2.jpg");
    }

    public Group getLayout() {
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

        for (double i = 0; i < 4; i++) {
            for (double j = 0; j < 4; j++) {
                ImageView imR = new ImageView(road);
                imR.setX(256);
                imR.setY(j * 64);

                ImageView imL = new ImageView(road);
                imL.setX(320);
                imL.setY(j * 64);
                imL.setRotate(180);

                grounds.add(imR);
                grounds.add(imL);
            }
        }

        for (double i = 0; i < 4; i ++) {
            ImageView im = new ImageView(roadCorner);
            im.setX(256 + i%2 * 64);
            im.setY(256 + (1<i && i<3? 0 : 1) * 64);
            im.setRotate(i*90);
            grounds.add(im);
        }

        Group rep = new Group();
        rep.getChildren().addAll(grounds);

        return rep;
    }
}
