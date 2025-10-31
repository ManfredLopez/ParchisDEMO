
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import model.*;
import view.*;

/**
 *
 * @author mjlop
 */

public class ControladorJuego implements ActionListener {

    //atributos tipo objeto (clase gui y paneles)
    private GUIJuego gui;
    private PanelControl panelControl;
    private PanelTablero panelTablero;

    //coords del paquete modelo
    private TableroCoordenadas tablero;
    private CoordenadasHome home;

    //atributo tipo objeto (clase ficha)
    private Ficha jugador;
    private Ficha oponente;

    //color de jugador y oponente
    private int colorJugadorId;
    private int colorOponenteId;

    //inicialización de clases
    private Colores colores = new Colores();
    private Random random = new Random();
    private Dice dado = new Dice(random, 0);

    public ControladorJuego(PanelControl panelControl, PanelTablero panelTablero, int pColorJugadorId) {
        this.panelControl = panelControl;
        this.panelTablero = panelTablero;
        this.colorJugadorId = colorJugadorId;

        tablero = new TableroCoordenadas();
        home = new CoordenadasHome();

       //identifica los ID red0, yellow1, blue2, green3
        
        colorJugadorId = pColorJugadorId;
        if (colorJugadorId < 0){
            colorJugadorId = 0;
        }
        if (colorJugadorId > 3) {
            colorJugadorId = 3;
        }
        
        if(colorJugadorId == 0){
            colorOponenteId = 1;
        }else if (colorJugadorId == 1) {
            colorOponenteId = 0;
        } else if (colorJugadorId == 2){
            colorOponenteId = 3;
        } else if (colorJugadorId == 3) {
            colorOponenteId = 2;
    }
        
    jugador = new Ficha(colorJugadorId);    
    oponente = new Ficha(colorOponenteId);
    
    panelControl.getBtnDice().addActionListener(this);
    panelControl.getBtnReinicio().addActionListener(this);
    
    Coordenadas cJugador = home.getHome(colorJugadorId);
    Coordenadas cOponente = home.getHome(colorJugadorId);
    panelTablero.moverFicha(colorJugadorId,cJugador.getX(),cJugador.getY());
    panelTablero.moverFicha(colorOponenteId,cOponente.getX(),cOponente.getY());
    
    panelControl.setTextoTurno("Turno:"+new Colores().nombre(colorJugadorId));
    panelControl.setNumeroDado(colorJugadorId);
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
