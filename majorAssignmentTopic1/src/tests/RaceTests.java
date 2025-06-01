package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Action;
import model.Character;
import model.CharacterClass;
import model.Race;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

public class RaceTests {

    private Character testCharacter;
    private Race HUMAN;

    @BeforeEach
    void setUp() {
        // Set up a race
        HUMAN = new Race("Human", 4, 4, 4, 4, 4, 4, 4, 10, 10, 10);

        // Set up a character class
        ArrayList<Action> warriorSkills = new ArrayList<>();
        CharacterClass WARRIOR = new CharacterClass("Warrior", 7, 2, 3, 3, 6, 4, 3, 15, 10, 5, warriorSkills);

        // Create a test character
        testCharacter = new Character("TestHero", HUMAN, WARRIOR);
    }

    @Test
    void testApplyRaceStat() {
        // Store initial stats
        int initialStrength = testCharacter.Strength;
        int initialIntellect = testCharacter.Intellect;
        int initialAgility = testCharacter.Agility;
        int initialHP = testCharacter.HP;
        // Apply race stats
        HUMAN.applyRaceStat(testCharacter);
        // Check if race stats are applied correctly
        assertEquals(initialStrength + HUMAN.Strength, testCharacter.Strength);
        assertEquals(initialIntellect + HUMAN.Intellect, testCharacter.Intellect);
        assertEquals(initialAgility + HUMAN.Agility, testCharacter.Agility);
        assertEquals(initialHP + HUMAN.bonusHP, testCharacter.HP);
    }

}
