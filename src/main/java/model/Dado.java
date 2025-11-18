package model;

import java.util.Random;

/**
 *
 * @author mjlop
 */
public class Dado {

    private Random random;

    public Dado() {
        random = new Random();
    }

    public int lanzar() {
        return random.nextInt(6) + 1;

    }
}