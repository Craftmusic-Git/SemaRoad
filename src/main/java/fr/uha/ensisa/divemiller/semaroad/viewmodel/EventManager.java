package fr.uha.ensisa.divemiller.semaroad.viewmodel;

import fr.uha.ensisa.divemiller.semaroad.graphics.view.LightPosition;

public class EventManager {

    EventManager em;

    private LightPosition lightPosition;

    private EventManager() {

    }

    public EventManager getEventManager() {
        if (em != null) return em;
        em = new EventManager();
        return em;
    }

    public void setLight(LightPosition lightPosition) {

    }
}
