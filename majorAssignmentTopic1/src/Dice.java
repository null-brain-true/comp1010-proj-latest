import java.util.Random;

public class Dice {
    Random random;

    public Dice() {
        random = new Random();
    }

    public int roll() {
        return random.nextInt(20) + 1;
    }
}
