package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import model.Action;
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
        testCharacter.HP = 100; testCharacter.setCurrentHP(100);
        testCharacter.Strength = 10;
        testCharacter.Intellect = 10;
        testCharacter.Agility = 10;
        testCharacter.Defense = 10;
        testCharacter.Evasion = 10;
        testCharacter.Resist = 10;
    }

    @Test
    void testDoTApplicationPerTurn() {
        StatusEffect poison = new StatusEffect("Poison", 10, 0, 0, 0, 0, 0, 0, 0, 0, 3); // 3 turns DoT
        testCharacter.addStatusEffect(poison);

        // Turn 1
        poison.applyTurnEffect(testCharacter);
        assertEquals(90, testCharacter.getCurrentHP());
        poison.duration--;
        assertFalse(poison.isExpired());

        // Turn 2
        poison.applyTurnEffect(testCharacter);
        assertEquals(80, testCharacter.getCurrentHP());
        poison.duration--;
        assertFalse(poison.isExpired());

        // Turn 3
        poison.applyTurnEffect(testCharacter);
        assertEquals(70, testCharacter.getCurrentHP());
        poison.duration--;
        assertTrue(poison.isExpired()); // Should be expired after this turn
    }

    @Test
    void testHoTApplicationPerTurn() {
        testCharacter.setCurrentHP(50); // Reduce HP to see healing
        StatusEffect regeneration = new StatusEffect("Regen", 0, 5, 0, 0, 0, 0, 0, 0, 0, 2); // 2 turns HoT
        testCharacter.addStatusEffect(regeneration);

        // Turn 1
        regeneration.applyTurnEffect(testCharacter);
        assertEquals(55, testCharacter.getCurrentHP());
        regeneration.duration--;
        assertFalse(regeneration.isExpired());

        // Turn 2
        regeneration.applyTurnEffect(testCharacter);
        assertEquals(60, testCharacter.getCurrentHP());
        regeneration.duration--;
        assertTrue(regeneration.isExpired());
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
    void testDebuffApplicationAndWearOff() {
        int initialAgility = testCharacter.Agility;
        int initialEvasion = testCharacter.Evasion;
        StatusEffect slowDebuff = new StatusEffect("Slow", 0, 0, 0, 0, -3, 0, 0, -2, 0, 1); // -3 Agi, -2 Eva, 1 turn
        testCharacter.addStatusEffect(slowDebuff);

        // Apply debuff
        slowDebuff.applyDebuff(testCharacter);
        assertTrue(slowDebuff.debuffApplied);
        assertEquals(initialAgility - 3, testCharacter.Agility);
        assertEquals(initialEvasion - 2, testCharacter.Evasion);

        // Simulate turn passing
        slowDebuff.duration--;
        slowDebuff.debuffWearOff(testCharacter);

        // After duration, debuff should wear off
        assertFalse(slowDebuff.debuffApplied);
        assertEquals(initialAgility, testCharacter.Agility);
        assertEquals(initialEvasion, testCharacter.Evasion);
        assertTrue(slowDebuff.isExpired());
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

    @Test
    void testDebuffDoesNotApplyMultipleTimes() {
        int initialDefense = testCharacter.Defense;
        StatusEffect defenseDebuff = new StatusEffect("Weakness", 0, 0, 0, 0, 0, 0, -3, 0, 0, 5);
        testCharacter.addStatusEffect(defenseDebuff);

        defenseDebuff.applyDebuff(testCharacter);
        assertEquals(initialDefense - 3, testCharacter.Defense);
        assertTrue(defenseDebuff.debuffApplied);

        defenseDebuff.applyDebuff(testCharacter); // Call again, should not apply
        assertEquals(initialDefense - 3, testCharacter.Defense); // Defense should be same
    }
}