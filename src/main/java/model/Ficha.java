package model;

/**
 *
 * @author mjlop
 */
public class Ficha {

    public int color;
    public int salida;
    public int totalCasillas;
    private String colorNombre;
    private int indice;
    private boolean posicionInicial;
    private boolean caminoWin;
    private int pasoWin;

    public Ficha(int pColor) {
        //atributo de la clase ficha y variable del constructor
        this.color = pColor;
        colorNombre = new Colores().nombre(pColor);
        this.salida = salida;
        this.totalCasillas = totalCasillas;
        indice = -1; // está en casa
        posicionInicial = true;
        pasoWin = 0;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getSalida() {
        return salida;
    }

    public void setSalida(int salida) {
        this.salida = salida;
    }

    public int getTotalCasillas() {
        return totalCasillas;
    }

    public void setTotalCasillas(int totalCasillas) {
        this.totalCasillas = totalCasillas;
    }

    public String getColorNombre() {
        return colorNombre;
    }

    public void setColorNombre(String colorNombre) {
        this.colorNombre = colorNombre;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public boolean isPosicionInicial() {
        return posicionInicial;
    }

    public void setPosicionInicial(boolean posicionInicial) {
        this.posicionInicial = posicionInicial;
    }

    public boolean isCaminoWin() {
        return caminoWin;
    }

    public void setCaminoWin(boolean caminoWin) {
        this.caminoWin = caminoWin;
    }

    public int getPasoWin() {
        return pasoWin;
    }

    public void setPasoWin(int pasoWin) {
        this.pasoWin = pasoWin;
    }

    public void reset() {
        indice = -1;
        pasoWin = 0;
        posicionInicial = true;
        caminoWin = true;
    }

}
