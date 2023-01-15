package fr.uha.ensisa.divemiller.semaroad;

import fr.uha.ensisa.divemiller.semaroad.layout.FixedObject;
import fr.uha.ensisa.divemiller.semaroad.layout.Roads;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SemaRoadApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FixedObject layout = new FixedObject();
        Scene scene = new Scene(layout.show(), 640, 640);


        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
