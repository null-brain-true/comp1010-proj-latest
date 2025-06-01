package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class CharacterManager {
    // store created characters
    private ArrayList<Character> characters;

    // initialize empty list
    public CharacterManager() {
        this.characters = new ArrayList<>();
    }

    public void addCharacter(Character character) {
        if (getCharacterByName(character.name) == null) {
            characters.add(character);
        } else {
            System.out.println("Character with name '" + character.name + "' already exists. Skipping.");
        }
    }

    // character creation
    public Character createCharacter(String name, String raceName, String className) {
        Race race = Race.getRace(raceName);
        CharacterClass characterClass = CharacterClass.getCharacterClass(className);

        if (race == null || characterClass == null) {
            System.out.println("Invalid race or class: " + raceName + ", " + className);
            return null;
        }

        Character character = new Character(name, race, characterClass);
        addCharacter(character);
        return character;
    }

    public void saveToCSV(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (Character c : characters) {
            writer.write(c.toCSV());
            writer.newLine();
        }
        writer.close();
    }

    public ArrayList<Character> getCharacters() {
        return new ArrayList<>(characters);
    }

    public Character getCharacterByName(String name) {
        for (Character c : characters) {
            if (c.name.equalsIgnoreCase(name)) {
                return c;
            }
        }
        return null;
    }

    public boolean removeCharacter(String name) {
        for (int i = 0; i < characters.size(); i++) {
            if (characters.get(i).name.equalsIgnoreCase(name)) {
                characters.remove(i);
                return true;
            }
        }
        return false;
    }

    public void clearCharacters() {
        characters.clear();
    }

    public static void main(String[] args) throws IOException {
        CharacterManager manager = new CharacterManager();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of characters to create: ");
        int count = Integer.parseInt(scanner.nextLine());
        // creates numbers of character you enter in terminal
        for (int i = 0; i < count; i++) {
            System.out.println("Character #" + (i + 1));
            System.out.print("Name: ");
            String name = scanner.nextLine();
            System.out.print("Race: ");
            String race = scanner.nextLine();
            System.out.print("Class: ");
            String characterClass = scanner.nextLine();

            manager.createCharacter(name, race, characterClass);
        }

        System.out.print("Enter the CSV filename to save to (e.g., yellowteam.csv): ");
        String fileName = scanner.nextLine();

        manager.saveToCSV(fileName);
        System.out.println("Characters saved to " + fileName);

        scanner.close();
    }
}
