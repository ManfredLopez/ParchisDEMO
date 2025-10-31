package model;

/**
 *
 * @author mjlop
 */
public class Colores {

    private int red = 0;
    private int yellow = 1;
    private int blue = 2;
    private int green = 3;

    public String nombre(int id) { //método para mostrar el
        switch (id) {              //texto de la etiqueta (setLblFichaTurno)
            case 0:
                return "Rojo";
            case 1:
                return "Amarillo";
            case 2:
                return "Azul";
            case 3:
                return "Verde";
            default:
                return "Rojo";

        }
    }

    public int idNombre(String nombre) {
    if (nombre.equals("Rojo")){
        return 0;
    }
    if (nombre.equals("Amarillo")){
        return 1;
    }
    if (nombre.equals("Azul")){
        return 2;
    }
    if (nombre.equals("Verde")){
        return 3;
    }
    return 0; 
    }

}
    
