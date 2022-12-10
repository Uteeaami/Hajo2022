package fi.utu.tech.assignment5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Remote extends Thread {

    private Hub hub;

    private enum Action {
        TURNOFF, TURNON, TOGGLE, TURNOFFALL, TURNONALL, ADD
    };

    private Random rnd = new Random();

    public Remote(Hub h) {
        super();
        this.hub = h;
    }

    /**Tehtävä 5
     * Lisätty synchronized avainsana hubin metodeigin, jotka ovat switch-casen sisällä
     * Saman asian ajaisi ilmeisesti se, jos käärittäisiin kyseinen switch-case synchronizedilla
     * Hieman mietitytti, että olisiko se liian 'matalalla' tasolla, jos synchronized lisättäisiin Light luokan metodeihin?
     * Varmaankin ei?
     *
     *
     *Hashmapista tehty myös säieturvallinen + Light luokan boolean arvosta tehty volatile
     *
     * Muutettu hieman turnOnLight ja TurnOffLight -metodien rakennetta
     *
     * Lisätty hieman satunnaisuutta sleep metodiin
     *
     *
     */

    @Override
    public void run() {
            while (true) {
                    int next = rnd.nextInt(Action.values().length);
                    Action nextAction = Action.values()[next];
                    List<Integer> lightIds = new ArrayList<>(hub.getLightIds());
                    int id = lightIds.get(rnd.nextInt(lightIds.size()));
                    switch (nextAction) {
                        case TURNOFF:
                            hub.turnOffLight(id);
                        case TOGGLE:
                            hub.toggleLight(id);
                            break;
                        case TURNON:
                            hub.turnOnLight(id);
                            break;
                        case ADD:
                            hub.addLight();
                            break;
                        case TURNOFFALL:
                            hub.turnOffAllLights();
                            break;
                        case TURNONALL:
                            hub.turnOnAllLights();
                            break;
                        default:
                            break;
                    }

                try {
                    Thread.sleep(100+rnd.nextInt(150));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }

}
