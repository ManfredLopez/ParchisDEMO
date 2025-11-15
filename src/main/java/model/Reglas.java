package model;

/**
 *
 * @author mjlop
 */
public class Reglas {

    private int totalCasillas = 68;
    // 0 rojo, 1 amarillo, 2 azul, 3 verde
    private int[] salida = new int[]{38, 4, 21, 55};
    //se maneja la ubicación con los índices según sus casillas
    private int[] entrada = new int[]{33, 67, 16, 50};

    public int getTotalCasillas() {
        return totalCasillas;
    }

    public boolean salida(int dice) {
        return dice == 5;
    }

    public int salidaId(int colorId) {
        return salida[colorId];
    }

    public boolean entradaWin(int colorId, int indiceRecorrido) {
        return indiceRecorrido == entrada[colorId];
    }

    public int avanzar(int indice, int diceValue) {
        int i;
        //1-6
        if (diceValue > 0) {
            for (i = 0; i < diceValue; i++) {
                indice++;
            }
        }
        if (indice == totalCasillas) {
            indice = 0;
        }

        return indice;
    }

    public boolean posicionFichaEntrada(int colorId, int indice, int diceValue) {
        int i;
        if (diceValue > 0) {
            for (i = 1; i <= diceValue; i++) {
                indice++;
                if (diceValue >= totalCasillas) {
                    totalCasillas--;
                }
                if (diceValue == indice) {
                    return true;
                }
            }
        } else {
            if (diceValue < 0) {
                for (i = -1; i >= diceValue; i--) {
                    indice++;
                }
                if (diceValue < totalCasillas) {
                    totalCasillas++;
                }
                if (diceValue == indice) {
                    return true;
                }
            
            }

        }
        return false;
    }

}
