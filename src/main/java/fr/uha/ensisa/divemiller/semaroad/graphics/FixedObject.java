package fr.uha.ensisa.divemiller.semaroad.graphics;

import fr.uha.ensisa.divemiller.semaroad.graphics.core.FixedGraphicObject;
import fr.uha.ensisa.divemiller.semaroad.graphics.view.Roads;
import javafx.scene.Group;

public class FixedObject implements FixedGraphicObject {

    public FixedObject() {
    }

    @Override
    public Group show() {
        Roads roads = new Roads();
        Group rep = new Group();
        rep.getChildren().addAll(roads.show());
        return rep;
    }
}
