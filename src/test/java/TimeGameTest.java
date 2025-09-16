import org.example.game.Game;
import org.example.game.GameCreator;
import org.junit.Test;

public class TimeGameTest {
    GameCreator gameCreator = new GameCreator();

    @Test
    public void timeOutTest() {
        int secondsElapsed = 0;
        Game game = gameCreator.createGame(3, 120);
    }

    @Test
    public void timeOutTestPerPlayer() {
        int secondsElapsed = 0;
        Game game = gameCreator.createGame(null, 120);
    }
}
