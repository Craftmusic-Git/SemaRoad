package fr.uha.ensisa.divemiller.semaroad.viewmodel;

import fr.uha.ensisa.divemiller.semaroad.graphics.view.LightPosition;

public class EventManager {

    private static EventManager em;

    private LightPosition lightPosition;

    private EventManager() {

    }

    public static EventManager getEventManager() {
        if (em != null)
            return em;
        em = new EventManager();
        return em;
    }

    public void setLight(LightPosition lightPosition) {
        this.lightPosition = lightPosition;
    }

    public LightPosition getLightPosition() {
        return lightPosition;
    }
}
