package model;

import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author mjlop
 */
public class Questions {

    private Random random = new Random();

    private String[][] easy = {
        {"System.out.println() imprime texto en pantalla?", "V"},
        {"Una variable int puede almacenar decimales?", "F"},
        {"El valor true significa verdadero?", "V"},
        {"El operador == compara igualdad entre números?", "V"},
        {"Un arreglo puede almacenar múltiples valores?", "V"},
        {"Los índices de un arreglo inician en 1?", "F"},
        {"La palabra class define una clase?", "V"},
        {"El ciclo for permite repetir instrucciones?", "V"},
        {"String es una clase en Java?", "V"},
        {"El operador + concatena textos?", "V"},
        {"La palabra break sirve para salir de un ciclo?", "V"},
        {"El método void puede devolver un valor?", "F"},
        {"El comentario // es de una sola línea?", "V"},
        {"public permite acceso desde otras clases?", "V"},
        {"Una variable puede cambiar su valor durante el programa?", "V"},};

    private String[][] medium = {
        {"Un método void puede devolver datos?", "F"},
        {"La sobrecarga de métodos usa nombres distintos?", "F"},
        {"Un constructor puede tener el mismo nombre que la clase?", "V"},
        {"El operador && representa un AND lógico?", "V"},
        {"while(true) crea un ciclo infinito?", "V"},
        {"Un atributo private puede accederse desde otra clase directamente?", "F"},
        {"ArrayList puede cambiar de tamaño dinámicamente?", "V"},
        {"== compara correctamente el contenido de dos Strings?", "F"},
        {"Un método puede tener múltiples return?", "V"},
        {"final evita que una variable cambie de valor?", "V"},
        {"Un método puede tener parámetros?", "V"},
        {"Una clase puede tener más de un constructor?", "V"},
        {"Un arreglo multidimensional es similar a una tabla?", "V"},
        {"this se refiere al objeto actual?", "V"},
        {"Se puede crear un objeto sin usar new?", "F"},};

        private String[][] hard = {{"La herencia permite compartir atributos y métodos?", "V"},
        {"Una clase abstracta puede instanciarse directamente?", "F"},
        {"Una interfaz puede tener métodos sin implementación?", "V"},
        {"El polimorfismo permite comportamientos diferentes con el mismo método?", "V"},
        {"El garbage collector libera memoria automáticamente?", "V"},
        {"static indica que el atributo pertenece a la clase?", "V"},
        {"Una excepción no manejada puede detener el programa?", "V"},
        {"try-catch captura errores en ejecución?", "V"},
        {"override redefine un método heredado?", "V"},
        {"La relación has-a describe herencia?", "F"},
        {"Un Thread permite ejecutar código en paralelo?", "V"},
        {"super invoca elementos de la clase padre?", "V"},
        {"O(n²) es más eficiente que O(n)?", "F"},
        {"HashMap almacena pares clave-valor?", "V"},
        {"La serialización convierte un objeto en bytes?", "V"},};

    public boolean preguntasVF(int difficulty) {
        String[][] banco;

        if (difficulty == 1) {
            banco = easy;
        } else if (difficulty == 2) {
            banco = medium;
        } else {
            banco = hard;
        }

        int indice = random.nextInt(banco.length);
        String question = banco[indice][0];
        String response = banco[indice][1];

        int option = JOptionPane.showConfirmDialog(
                null, question + "\n\n Seleccione: Si = true, No = false",
                "Pregunta", JOptionPane.YES_NO_OPTION);

        String respuestaJugador;

        if (option == JOptionPane.YES_OPTION) {
            respuestaJugador = "V";
        } else if (option == JOptionPane.NO_OPTION) {
            respuestaJugador = "F";
        } else {
            return false; // cancelado
        }

        boolean correct = respuestaJugador.equals(response);

        String mensaje;

        if (correct) {
            mensaje = switch (difficulty) {
                case 1 ->
                    "Correcto, ganó 1 punto.";
                case 2 ->
                    "Correcto, ganó 2 puntos.";
                default ->
                    "Correcto, ganó 3 puntos.";
            };
        } else {
            mensaje = switch (difficulty) {
                case 1 ->
                    "Incorrecto, perdió 1 punto.";
                case 2 ->
                    "Incorrecto, perdió 2 puntos.";
                default ->
                    "Incorrecto, perdió 3 puntos.";
            };
        }

        JOptionPane.showMessageDialog(null, mensaje);
        return correct;
    }
}
