package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.Action;
import model.Character;
import model.CharacterClass;
import model.Race;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class ActionTests {

    private Character testCharacter;
    private Character targetCharacter;
    private Action attackAction;

    @BeforeEach
    void setUp() {
        // Set up a test character
        Race HUMAN = new Race("Human", 4, 4, 4, 4, 4, 4, 4, 10, 10, 10);
        ArrayList<Action> warriorSkills = new ArrayList<>();
        CharacterClass WARRIOR = new CharacterClass("Warrior", 7, 2, 3, 3, 6, 4, 3, 15, 10, 5, warriorSkills);
        testCharacter = new Character("TestHero", HUMAN, WARRIOR);

        // Set up a target character
        targetCharacter = new Character("TargetHero", HUMAN, WARRIOR);

        // Set up an attack action
        attackAction = new Action("Slash", "attack", "physical", 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    @Test
    void testAttackActionEffect() {
        // Initial HP of target
        int initialHP = targetCharacter.getCurrentHP();

        // Perform the action
        attackAction.effect(testCharacter, targetCharacter, true);

        // Check if target's HP has decreased correctly
        assertEquals(initialHP, targetCharacter.getCurrentHP());
    }

    @Test
    void testInsufficientResources() {
        // Set the test character's resources to be insufficient
        testCharacter.setCurrentHP(0);
        testCharacter.setCurrentSP(0);
        testCharacter.setCurrentMP(0);

        // Perform the action
        attackAction.effect(testCharacter, targetCharacter, true);

        // Check if target's HP remains unchanged
        assertEquals(targetCharacter.getCurrentHP(), targetCharacter.getCurrentHP());
    }

}
