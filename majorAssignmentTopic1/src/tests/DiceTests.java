package tests;

import org.junit.jupiter.api.Test;
import model.Dice;
import static org.junit.jupiter.api.Assertions.*;

public class DiceTests {

    @Test
    void testDiceRoll() {
        Dice dice = new Dice();
        int roll = dice.roll();

        // Check if the roll is within the expected range
        assertTrue(roll >= 1 && roll <= 20, "Dice roll should be between 1 and 20");
    }

}
