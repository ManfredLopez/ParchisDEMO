package model;

import java.util.Random;

/**
 *
 * @author mjlop
 */
public class Dice {

    private Random random;
    private int numero;

    public Dice(Random random, int numero) {
        this.random = random;
        this.numero = numero;
    }

    public int lanzar() {
        numero = random.nextInt(6) + 1;
        return numero;
    }

    public int getCurrentValue() {
        return numero;
    }

}
