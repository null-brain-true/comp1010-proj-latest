public class SaveManager {
    public static void saveCharacters(String filename, List<Character> characters) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Character c : characters) {
                writer.println(c.name + "," + c.HP + "," + c.SP + "," + c.MP);
            }
            System.out.println("Characters saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving characters: " + e.getMessage());
        }
    }

    public static List<String> loadCharacterLines(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading characters: " + e.getMessage());
        }
        return lines;
    }
}
