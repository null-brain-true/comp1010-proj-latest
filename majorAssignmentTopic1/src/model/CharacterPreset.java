package model;

import java.util.List;

public class CharacterPresetCreator {
    public static void main(String[] args) {
        List<Character> sampleCharacters = GameManager.createSampleCharacters();
        SaveManager.saveCharacterPresets("CharacterPreset.csv", sampleCharacters);
    }
}
