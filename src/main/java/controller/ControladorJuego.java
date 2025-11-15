
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import model.*;
import view.*;

/**
 *
 * @author mjlop
 *
 */
public class ControladorJuego implements ActionListener {

    // ---- vista ----
    private PanelControl panelControl;
    private PanelTablero panelTablero;

    // ---- modelo ----
    private TableroCoordenadas tablero;
    private CoordenadasHome home;
    private Reglas reglas;
    private Colores colores;
    private Dado dado;

    private Ficha jugador;
    private Ficha oponente;

    // ---- colores ----
    private int colorJugadorId;
    private int colorOponenteId;

    // true = jugador, false = oponente
    private boolean turno = true;

    //Fase 3
    // Timer de turno (cuenta regresiva)
    private Temporizador temporizador;
    // preguntas
    private int facil = 1;
    private int media = 2;
    private int dificil = 3;

// Preguntas
    private Questions preguntas = new Questions();

    //Fase 4
    private Sound sonido;
    
    // constructor para identificar el color con un id
    public ControladorJuego(int colorId) {
        iniciarModelo();

        // validamos el color para evitar errores (por si cae fuera de rango)
        if (colorId < 0) {
            colorJugadorId = 0;
        } else if (colorId > 3) {
            colorJugadorId = 3;
        } else {
            colorId = colorJugadorId;
        }
        colorOponenteId = emparejarColor(colorJugadorId);

        jugador = new Ficha(colorJugadorId);
        oponente = new Ficha(colorOponenteId);
    }
//Llamar al modelo desde un metodo

    private void iniciarModelo() {
        tablero = new TableroCoordenadas();
        home = new CoordenadasHome();
        reglas = new Reglas();
        colores = new Colores();
        dado = new Dado();
        //Fase3
        preguntas = new Questions();
        //Fase4
        sonido = new Sound();
    }

    //contar la vista a los paneles de la vista.
    public void conectarVistas(PanelControl pc, PanelTablero pt) {
        panelControl = pc;
        panelTablero = pt;

        // listeners (el controlador escucha los botones)
        panelControl.getBtnDice().addActionListener(this);
        panelControl.getBtnReinicio().addActionListener(this);

        // estado inicial en Interfaz gráfica
        ubicarEnHome();
        panelControl.setTextoNumeroDado(0);
        panelControl.setTextoTurno(colores.nombre(colorJugadorId));
        panelControl.setTxtPuntosJugador(jugador.getPuntos());
        panelControl.setTxtPuntosOponente(oponente.getPuntos());
        panelControl.setTxtTiempo(0);

        // temporizador global de partida (cuenta hacia arriba)
        temporizador = new Temporizador(panelControl);
        temporizador.start();
        
        sonido.musicaFondo();
    }

    //Emparejamiento
    // 0<->1 (rojo-amarillo), 2<->3 (azul-verde)
    private int emparejarColor(int id) {
        switch (id) {
            case 0:
                return 1;
            case 1:
                return 0;
            case 2:
                return 3;
            default:
                return 2;
        }
    }

    private void ubicarEnHome() {
        Coordenadas cj = home.getHome(colorJugadorId);
        Coordenadas co = home.getHome(colorOponenteId);
        panelTablero.moverFicha(colorJugadorId, cj.getX(), cj.getY());
        panelTablero.moverFicha(colorOponenteId, co.getX(), co.getY());
    }

    //Operador logico de negacion !
    private void cambiarTurnoYActualizar() {
        // alternar turno
        turno = !turno; // (sin operador ternario, estilo simple)
        // actualizar label del turno
        String nombre;
        if (turno) {
            nombre = colores.nombre(colorJugadorId);
        } else {
            nombre = colores.nombre(colorOponenteId);
        }
        panelControl.setTextoTurno(nombre);
    }

    // Metodo jugar, tiene la logica del juego
    public void jugar() {
        int textoDado = dado.lanzar();//usamos el metodo lanzar
        panelControl.setTextoNumeroDado(textoDado);

        Ficha actual;
        int idColor;

        if (turno) {
            actual = jugador;
            idColor = colorJugadorId;
        } else {
            actual = oponente;
            idColor = colorOponenteId;
        }

        int estado = obtenerEstado(actual); // 0=casa, 1=ruta, 2=win

        switch (estado) {
            case 0: // en casa
                EnCasa(actual, idColor, textoDado);
                break;
            case 1: // en ruta
                EnRuta(actual, idColor, textoDado);
                break;
            case 2: // en camino win
                EnWin(actual, idColor, textoDado);
                break;
            default:
                break;
        }

        cambiarTurnoYActualizar();
    }

    private int obtenerEstado(Ficha f) {
        if (f.isPosicionInicial()) {
            return 0; // casa
        } else if (!f.isCaminoWin()) {
            return 1; // ruta
        } else {
            return 2; // win
        }
    }

    private void EnCasa(Ficha ficha, int idColor, int tiro) {
        if (reglas.salida(tiro)) {
            // al salir, recupera puntos iniciales
            ficha.reiniciarPuntos();
            panelControl.setTxtPuntosJugador(jugador.getPuntos());
            panelControl.setTxtPuntosOponente(oponente.getPuntos());
            //identificar las reglas
            int salida = reglas.salidaId(idColor);
            
            ficha.setIndice(salida);
            ficha.setPosicionInicial(false);
            ficha.setCaminoWin(false);
            ficha.setPasoWin(0);
            moverFicha(ficha, idColor);

            // en la salida (ruta) hay pregunta fácil
            PreguntaFacil(ficha, idColor);
        }
        // si no sale, no se mueve (se cambia turno en jugar())
    }

    private void EnRuta(Ficha ficha, int idColor, int tiro) {
        int desde = ficha.getIndice();

        if (reglas.posicionFichaEntrada(idColor, desde, tiro)) {
            // entra al camino WIN (sin preguntas intermedias)
            entrarEnWin(ficha, idColor);
            return;
        }
        int nuevo = reglas.avanzar(desde, tiro);
        ficha.setIndice(nuevo);
        moverFicha(ficha, idColor);
        // colisión en el arreglo principal
        if (Colision()) {
            PreguntaColisionMedia();
            return;
        }
        // pregunta fácil en casilla normal
        PreguntaFacil(ficha, idColor);
    }
//fase 2 y 3

    private void EnWin(Ficha f, int idColor, int tiro) {
        int largo = tablero.getAvanzarFichaWin(idColor);
        int next = f.getPasoWin() + tiro;

        if (next >= largo) {
            // META
            Coordenadas m = tablero.getMeta(idColor);
            panelTablero.moverFicha(idColor, m.getX(), m.getY());
            f.setPasoWin(largo - 1);

            // META: pregunta difícil; si falla, HOME
            PreguntaConReglas(f, idColor, dificil, true);
        } else {
            f.setPasoWin(next);
            moverFichaWin(f, idColor, next);
            // Camino WIN intermedio: sin preguntas
            PreguntaConReglas(f, idColor, media, true);
        }
    }

    private void entrarEnWin(Ficha f, int idColor) {
        f.setCaminoWin(true);
        f.setPasoWin(0);
        moverFichaWin(f, idColor, 0);
    }

    private void moverFicha(Ficha f, int idColor) {
        Coordenadas p = tablero.getPosition(f.getIndice());
        panelTablero.moverFicha(idColor, p.getX(), p.getY());
    }

    private void moverFichaWin(Ficha f, int idColor, int paso) {
        Coordenadas p = tablero.getCaminoWin(idColor, paso);
        panelTablero.moverFicha(idColor, p.getX(), p.getY());
    }
//fase3

    private boolean Colision() {
        if (jugador.isPosicionInicial() || oponente.isPosicionInicial()) {
            return false;
        }
        if (jugador.isCaminoWin() || oponente.isCaminoWin()) {
            return false;
        }
        return jugador.getIndice() == oponente.getIndice();
    }

    private void PreguntaColisionMedia() {
        // la ficha alcanzada es la que NO movió
        Ficha alcanzada;
        int idAlcanzada;

        if (turno) { // movió jugador
            alcanzada = oponente;
            idAlcanzada = colorOponenteId;
        } else {
            alcanzada = jugador;
            idAlcanzada = colorJugadorId;
        }

        PreguntaConReglas(alcanzada, idAlcanzada, media, true); // si falla -> HOME
    }

    private void PreguntaFacil(Ficha f, int idColor) {
        PreguntaConReglas(f, idColor, facil, false);
    }

    private void PreguntaConReglas(Ficha f, int idColor, int dificultad, boolean mandarHomeSiFalla) {
        boolean escucha = preguntas.preguntasVF(dificultad);

        if (escucha) {
            // sumar puntos según dificultad
            if (dificultad == facil) {
                f.sumarPuntos(1);
            } else if (dificultad == media) {
                f.sumarPuntos(2);
            } else {
                f.sumarPuntos(3);
            }
            //después del else de victoria
            sonido.detenerFondo();
            sonido.win();
            sonido.lose();
        } else {
            // restar puntos según dificultad
            if (dificultad == facil) {
                f.restarPuntos(1);
            } else if (dificultad == media) {
                f.restarPuntos(2);
            } else {
                f.restarPuntos(3);
            }
            //después del else de derrota
            sonido.detenerFondo();
            sonido.win();
            sonido.lose();
            
            // si debe ir a home (meta/colisión) o se quedó sin puntos
            if (mandarHomeSiFalla || f.sinPuntos()) {
                f.enviarHome();
                Coordenadas c = home.getHome(idColor);
                panelTablero.moverFicha(idColor, c.getX(), c.getY());
            }
        }
        // refrescar interfaz graficas de puntos
        if (idColor == colorJugadorId) {
            panelControl.setTxtPuntosJugador(f.getPuntos());
        } else {
            panelControl.setTxtPuntosOponente(f.getPuntos());
        }
    }

    // Boton reiniciar y sus metodos
    public void reiniciar() {
        jugador.reset();
        oponente.reset();
        panelControl.setTextoNumeroDado(0);
        panelControl.setTxtPuntosJugador(jugador.getPuntos());
        panelControl.setTxtPuntosOponente(oponente.getPuntos());
        turno = true;
        ubicarEnHome();
        cambiarTurnoYActualizar();
        temporizador.reiniciar();
        //Fase 4, método para reiniciar sonido
        sonido.detenerFondo();
        sonido.musicaFondo();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object fuente = e.getSource();
        if (fuente == panelControl.getBtnDice()) {
            sonido.click();
            jugar();
        } else if (fuente == panelControl.getBtnReinicio()) {
            sonido.click();
            reiniciar();
        }
    }
}
