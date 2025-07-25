import org.example.api.AIPlayer;
import org.example.api.GameEngine;
import org.example.api.RuleEngine;
import org.example.boards.TicTacBoard;
import org.example.game.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GamePlayTest {

    public GameState play(int[][] playerMoves, int[][] aiMoves) {
        GameEngine gameEngine = new GameEngine();
        Board board = gameEngine.start("TicTacBoard");
        RuleEngine ruleEngine = new RuleEngine();

        Player user = new Player("X");
        Player computer = new AIPlayer("O");

        int totalMoves = Math.max(playerMoves.length, aiMoves.length);
        for (int i = 0; i < totalMoves; i++) {
            if (i < playerMoves.length && !ruleEngine.getState(board).isGameOver()) {
                int[] move = playerMoves[i];
                gameEngine.move(board, new Move(user, new Cell(move[0], move[1])));
            }

            if (i < aiMoves.length && !ruleEngine.getState(board).isGameOver()) {
                int[] move = aiMoves[i];
                gameEngine.move(board, new Move(computer, new Cell(move[0], move[1])));
            }
        }
        ((TicTacBoard)board).print();
        System.out.println();
        return ruleEngine.getState(board);
    }


    @Test
    public void checkRowWin() {
        int[][] playerMoves = {{2, 0}, {2, 1}, {2, 2}};
        int[][] aiMoves = {{0, 0}, {0, 1}, {0, 2}};
        GameState state = play(playerMoves, aiMoves);
        assertEquals("X", state.getWinner());
        assertTrue(state.isGameOver());
    }

    @Test
    public void checkColWin() {
        int[][] playerMoves = {{0, 1}, {1, 1}, {2, 1}};
        int[][] aiMoves = {{0, 0}, {1, 0}, {2, 0}}; // O occupies full column 0
        GameState state = play(playerMoves, aiMoves);
        assertEquals("X", state.getWinner());
        assertTrue(state.isGameOver());
    }

    @Test
    public void checkDiag1Win() {
        int[][] playerMoves = {{0, 0}, {1, 1}, {2, 2}};
        int[][] aiMoves = {{2, 0}, {2, 1}, {1, 2}};
        GameState state = play(playerMoves, aiMoves);
        assertEquals("X", state.getWinner());
        assertTrue(state.isGameOver());
    }

    @Test
    public void checkDiag2Win() {
        int[][] playerMoves = {{0, 2}, {1, 1}, {2, 0}};
        int[][] aiMoves = {{0, 1}, {2, 1}, {2, 2}};
        GameState state = play(playerMoves, aiMoves);
        assertEquals("X", state.getWinner());
        assertTrue(state.isGameOver());
    }

    @Test
    public void checkDraw() {
        int[][] playerMoves = {{0, 0}, {0, 1}, {1, 2}, {2, 0}, {2, 2}};
        int[][] aiMoves = {{0, 2}, {1, 0}, {1, 1}, {2, 1}};
        GameState state = play(playerMoves, aiMoves);
        assertEquals("-", state.getWinner());
        assertTrue(state.isGameOver());
    }

}