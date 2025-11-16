package model;

import javax.swing.SwingUtilities;
import view.PanelControl;

/**
 *
 * @author mjlop
 */
public class Temporizador extends Thread {

    private PanelControl panel;
    private boolean active;
    private int seconds;

    public Temporizador(PanelControl pPanel) {
        this.panel = pPanel;
        this.active = true;
        this.seconds = 0;
    }

public void run() {
    while (active) {
        try {
            Thread.sleep(1000);
            seconds++;

            SwingUtilities.invokeLater(() -> {
                panel.setTxtTiempo(seconds);
            });

        } catch (InterruptedException e) {
            break;
        }
    }
}

    public void detener() {
        active = false;
        // viene de la clase Thread
        interrupt();
    }

    public void reiniciar() {
        seconds = 0;
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                panel.setTxtTiempo(seconds);
            }
        });
    }

}
