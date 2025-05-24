package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Action;
import model.Character;
import model.CharacterClass;
import model.Race;
import model.StatusEffect;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class CharacterTests {

    private Character testCharacter;
    private Race HUMAN;
    private CharacterClass WARRIOR;

    @BeforeEach
    void setUp() {
        HUMAN = new Race("Human", 4, 4, 4, 4, 4, 4, 4, 10, 10, 10);
        ArrayList<Action> warriorSkills = new ArrayList<>();
        // Add a dummy action to the warrior class for initialization purposes, if needed
        warriorSkills.add(new Action("Dummy Attack", "attack", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0));
        WARRIOR = new CharacterClass("Warrior", 7, 2, 3, 3, 6, 4, 3, 15, 10, 5, warriorSkills);
        testCharacter = new Character("TestHero", HUMAN, WARRIOR);
    }

    @Test
    void testCharacterInitialization() {
        assertEquals("TestHero", testCharacter.name);
        // Base HP (50) + Race bonus (10) + Class bonus (15)
        assertEquals(50 + 10 + 15, testCharacter.HP);
        // Base SP (100) + Race bonus (10) + Class bonus (10)
        assertEquals(100 + 10 + 10, testCharacter.SP);
        // Base MP (100) + Race bonus (10) + Class bonus (5)
        assertEquals(100 + 10 + 5, testCharacter.MP);
        // Race Strength (4) + Class Strength (7)
        assertEquals(4 + 7, testCharacter.Strength);
        // Race Intellect (4) + Class Intellect (2)
        assertEquals(4 + 2, testCharacter.Intellect);
        // Ensure current HP/SP/MP are initialized to max
        assertEquals(testCharacter.HP, testCharacter.getCurrentHP());
        assertEquals(testCharacter.SP, testCharacter.getCurrentSP());
        assertEquals(testCharacter.MP, testCharacter.getCurrentMP());
    }

    @Test
    void testIsDefeated() {
        testCharacter.setCurrentHP(10);
        assertFalse(testCharacter.isDefeated());
        testCharacter.setCurrentHP(0);
        assertTrue(testCharacter.isDefeated());
        testCharacter.setCurrentHP(-5); // Below zero should also be defeated
        assertTrue(testCharacter.isDefeated());
    }

    @Test
    void testOutOfSP() {
        testCharacter.setCurretSP(10);
        assertFalse(testCharacter.outOfSP());
        testCharacter.setCurretSP(0);
        assertTrue(testCharacter.outOfSP());
        testCharacter.setCurretSP(-1);
        assertTrue(testCharacter.outOfSP());
    }

    @Test
    void testOutOfMP() {
        testCharacter.setCurrentMP(10);
        assertFalse(testCharacter.outOfMP());
        testCharacter.setCurrentMP(0);
        assertTrue(testCharacter.outOfMP());
        testCharacter.setCurrentMP(-1);
        assertTrue(testCharacter.outOfMP());
    }

    @Test
    void testAddAndRemoveStatusEffect() {
        StatusEffect effect = new StatusEffect("TestEffect", 0, 0, 1, 0, 0, 0, 0, 0, 0, 2);
        testCharacter.addStatusEffect(effect);
        assertTrue(testCharacter.getActiveStatusEffects().contains(effect));
        assertEquals(1, testCharacter.getActiveStatusEffects().size());

        testCharacter.removeStatusEffect(effect);
        assertFalse(testCharacter.getActiveStatusEffects().contains(effect));
        assertEquals(0, testCharacter.getActiveStatusEffects().size());
    }

    @Test
    void testStatBonusesFromRaceAndClassApplied() {
        Character customCharacter = new Character("Custom", HUMAN, WARRIOR);
        assertEquals(HUMAN.Strength + WARRIOR.Strength, customCharacter.Strength);
        assertEquals(HUMAN.Intellect + WARRIOR.Intellect, customCharacter.Intellect);
        assertEquals(HUMAN.HP + WARRIOR.bonusHP + 50, customCharacter.HP); // Including base HP
    }

    @Test
    void testActionsAppliedFromClass() {
        Character customCharacter = new Character("Custom", HUMAN, WARRIOR);
        // The WARRIOR class has one dummy action added in setUp, check if it's there
        assertFalse(customCharacter.actions.isEmpty());
        assertEquals(1, customCharacter.actions.size());
        assertEquals("Dummy Attack", customCharacter.actions.get(0).name);
    }
}