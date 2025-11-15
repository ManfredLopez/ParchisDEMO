package model;
        
/**
*
* @author mjlop
*/

import java.net.URL;
import javax.sound.sampled.*;

public class Sound {

    private Clip musica;

    private Clip cargar(String ruta) {
        try {
            URL u = getClass().getResource(ruta);
            AudioInputStream ais = AudioSystem.getAudioInputStream(u);
            Clip c = AudioSystem.getClip();
            c.open(ais);
            return c;
        } catch (Exception e) {
            return null;
        }
    }

    public void musicaFondo() {
    musica = cargar("/sounds/ambiente.wav");
    if (musica != null) musica.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void musicaFondoPrincipal() {
    musica = cargar("/sounds/fondoprincipal.wav");
    if (musica != null) musica.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void detenerFondo() {
    if (musica != null) musica.stop();
    }
    public void click() {
    Clip c = cargar("/sounds/click.wav");
    if (c != null) c.start();
    }
    public void win() {
    Clip c = cargar("/sounds/acierto.wav");
    if (c != null) c.start();
    }
    public void lose() {
    Clip c = cargar("/sounds/fallo.wav");
    if (c != null) c.start();
    }
    public void metaSound() {
    Clip c = cargar("/sounds/metaSound.wav");
    if (c != null) c.start();
    }
}



