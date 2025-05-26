package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SaveManager {

    /**
     * Saves a list of Character objects to a CSV file.
     *
     * @param filename The name of the CSV file to create/overwrite.
     * @param characters The list of Character objects to save.
     */
    public static void saveCharacterPresets(String filename, List<Character> characters) {
        try (FileWriter fileWriter = new FileWriter(filename);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            // Write CSV header
            // We're including all the public attributes from your Character class
            printWriter.println("Name,HP,SP,MP,Strength,Intellect,Agility,Initiative,Defense,Evasion,Resist,Race,CharacterClass");

            // Write character data
            for (Character character : characters) {
                printWriter.printf("%s,%d,%d,%d,%d,%d,%d,%d,%d,%d,%d,%s,%s%n",
                                   character.name,
                                   character.HP,
                                   character.SP,
                                   character.MP,
                                   character.Strength,
                                   character.Intellect,
                                   character.Agility,
                                   character.Initiative,
                                   character.Defense,
                                   character.Evasion,
                                   character.Resist,
                                   character.race, // Assuming Race is an enum with a name() method or a class with a getName()
                                   character.characterClass); // Assuming CharacterClass is an enum with a name() method or a class with a getName()
            }

            System.out.println("Character presets saved successfully to " + filename);

        } catch (IOException e) {
            System.err.println("Error saving character presets: " + e.getMessage());
            e.printStackTrace(); // For more detailed error information
        }
    }
}