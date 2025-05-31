import model.Game;
import model.GameManager;

public class Client {
    public static void main(String[] args) throws Exception {
        Game game = GameManager.createSampleGame();
        game.start();
    }
}