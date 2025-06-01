package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import model.Character;
import model.CharacterClass;
import model.Race;
import model.StatusEffect;

import static org.junit.jupiter.api.Assertions.*;

public class StatusEffectTests {

    private Character testCharacter;
    private Race DUMMY_RACE;
    private CharacterClass DUMMY_CLASS;

    @BeforeEach
    void setUp() {
        DUMMY_RACE = new Race("Dummy", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        DUMMY_CLASS = new CharacterClass("Dummy", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, new ArrayList<>());
        testCharacter = new Character("EffectSubject", DUMMY_RACE, DUMMY_CLASS);

        // Ensure character stats are at a known state
        testCharacter.HP = 100;
        testCharacter.setCurrentHP(100);
        testCharacter.Strength = 10;
        testCharacter.Intellect = 10;
        testCharacter.Agility = 10;
        testCharacter.Defense = 10;
        testCharacter.Evasion = 10;
        testCharacter.Resist = 10;
    }

    @Test
    void testBuffApplicationAndWearOff() {
        int initialStrength = testCharacter.Strength;
        int initialDefense = testCharacter.Defense;
        StatusEffect strengthBuff = new StatusEffect("Might", 0, 0, 5, 0, 0, 0, 3, 0, 0, 1); // +5 Str, +3 Def, 1 turn
        testCharacter.addStatusEffect(strengthBuff);

        // Apply buff
        strengthBuff.applyBuff(testCharacter);
        assertTrue(strengthBuff.buffApplied);
        assertEquals(initialStrength + 5, testCharacter.Strength);
        assertEquals(initialDefense + 3, testCharacter.Defense);

        // Simulate turn passing
        strengthBuff.duration--;
        strengthBuff.buffWearOff(testCharacter);

        // After duration, buff should wear off
        assertFalse(strengthBuff.buffApplied);
        assertEquals(initialStrength, testCharacter.Strength);
        assertEquals(initialDefense, testCharacter.Defense);
        assertTrue(strengthBuff.isExpired());
    }

    @Test
    void testBuffDoesNotApplyMultipleTimes() {
        int initialStrength = testCharacter.Strength;
        StatusEffect strengthBuff = new StatusEffect("Might", 0, 0, 5, 0, 0, 0, 0, 0, 0, 5);
        testCharacter.addStatusEffect(strengthBuff);

        strengthBuff.applyBuff(testCharacter);
        assertEquals(initialStrength + 5, testCharacter.Strength);
        assertTrue(strengthBuff.buffApplied);

        strengthBuff.applyBuff(testCharacter); // Call again, should not apply
        assertEquals(initialStrength + 5, testCharacter.Strength); // Strength should be same
    }
}