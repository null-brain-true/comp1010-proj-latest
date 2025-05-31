package tests;

import model.CharacterClass;
import model.Action;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CharacterClassTests {

    @Test
    public void testConstructorSetsValuesCorrectly() {
        ArrayList<Action> abilities = new ArrayList<>();
        CharacterClass warrior = new CharacterClass("Warrior", 10, 5, 3, 1, 8, 2, 4, 100, 50, 25, abilities);

        assertEquals("Warrior", warrior.name);
        assertEquals(10, warrior.Strength);
        assertEquals(100, warrior.bonusHP);
    }

    @Test
    public void testAbilitiesInitiallyEmptyOrSet() {
        CharacterClass mage = new CharacterClass("Mage", 2, 10, 3, 1, 2, 5, 6, 70, 40, 60, new ArrayList<>());
        assertTrue(mage.abilities.isEmpty());
    }
}