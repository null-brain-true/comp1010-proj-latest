package model;

import java.util.Random;

//roll a d20 dice
public class Dice {
    public Random random;

    public Dice() {
        random = new Random();
    }

    // roll between 1-20
    public int roll() {
        return random.nextInt(20) + 1;
    }
}
