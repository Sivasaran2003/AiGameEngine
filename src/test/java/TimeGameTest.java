import org.example.game.*;
import org.junit.Assert;
import org.junit.Test;

public class TimeGameTest {
    GameCreator gameCreator = new GameCreator();

    @Test
    public void timeOutTest() {
        int secondsElapsed = 0;
        Game game = gameCreator.createGame(3, 11);
        Player x = new Player("X");
        Player o = new Player("O");

        Cell cc00 = Cell.getCell(0, 0);
        Cell cc01 = Cell.getCell(0, 1);
        Cell cc02 = Cell.getCell(0, 2);
        Cell cc10 = Cell.getCell(1,  0);

        int ts = 50;
        game.move(new Move(x, cc00), ts);
        game.move(new Move(x, cc01), ts);
        game.move(new Move(x, cc02), ts);
        game.move(new Move(x, cc10), 100000);

        Assert.assertEquals(game.getWinner().getPlayerSymbol(), o.getPlayerSymbol());
    }

    @Test
    public void timeOutTestPerPlayer() {
        int secondsElapsed = 0;
        Game game = gameCreator.createGame(null, 120);
    }
}
