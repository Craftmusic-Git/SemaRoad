package fr.uha.ensisa.divemiller.semaroad.layout;

import javafx.scene.Group;

public class FixedObject implements GraphicObject {

    public FixedObject() {

    }

    @Override
    public Group show() {
        Roads roads = new Roads();
        TrafficLight lights = new TrafficLight(LightPosition.HORIZONTAL);
        Group rep = new Group();
        rep.getChildren().addAll(roads.show(), lights.show());
        return rep;
    }
}
