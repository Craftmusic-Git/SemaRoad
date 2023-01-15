package fr.uha.ensisa.divemiller.semaroad;

import fr.uha.ensisa.divemiller.semaroad.graphics.DynamicObjects;
import fr.uha.ensisa.divemiller.semaroad.graphics.FixedObject;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Semaphore;

public class SemaRoadApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, InterruptedException {
        Semaphore startedModel = new Semaphore(0);
        Thread logic = new Thread(new ModelLaucher(startedModel));
        logic.start();
        startedModel.acquire();

        FixedObject layout = new FixedObject();
        final Canvas canvas = new Canvas(640, 640);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        DynamicObjects dynamicObjects = new DynamicObjects(gc);
        dynamicObjects.show(gc);

        Group graphicsObjects = new Group(layout.show(), canvas);
        Scene scene = new Scene(graphicsObjects, 640, 640);

        stage.setScene(scene);
        dynamicObjects.start();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
