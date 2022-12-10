package fi.utu.tech.assignment5;


public class Light {

    /* NOTE: Light-luokka on synkronoitu pääasiassa
     * toggle-toiminnallisuuden vuoksi, jolloin samanaikaiset toggle-yritykset
     * "jonoutuvat" peräkkäin. Teoriassa voisi argumentoida, että onko kyseinen
     * toimintatapa välttämätön. Mikäli synkronointiblokkia ei käytetä, pitäisi
     * booleanit merkata volatileiksi, jotta ajantasainen tieto päätyisi varmasti hubille
     */

    private boolean powerOn = false;
    private int id;

    /**
     *
     * @param id The id of the lamp (should be the same as in the hub for now)
     */
    public Light(int id) {
        this.id = id;
    }

    /**
     * Turn lamp on
     */
    public synchronized void turnOn() {
        this.powerOn = true;
    }

    /**
     * Turn lamp off
     */
    public synchronized void turnOff() {
        this.powerOn = false;
    }

    /**
     * Toggle the lamp on/off depending on the current state
     */
    public synchronized void toggle() {
        // We could have gotten away with just using volatile boolean
        // but because of this here, everybody gets synchronization
        powerOn = !powerOn;
    }

    /**
     *
     * @return Is the lamp currently powered on?
     */
    public synchronized boolean isPowerOn() {
        return powerOn;
    }

    public synchronized String toString() {
        return String.format("Light %d is set %s", id, isPowerOn() ? "ON": "OFF");
    }

}


    

