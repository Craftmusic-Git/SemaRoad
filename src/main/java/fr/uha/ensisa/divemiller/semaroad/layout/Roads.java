package fr.uha.ensisa.divemiller.semaroad.layout;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Roads {

    Image road;

    Image roadCorner;

    Image ground;

    public Roads() {
        ground = new Image("file:src/main/resources/fr/uha/ensisa/divemiller/semaroad/ground.jpg");
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

        Group rep = new Group();
        rep.getChildren().addAll(grounds);

        return rep;
    }
}
