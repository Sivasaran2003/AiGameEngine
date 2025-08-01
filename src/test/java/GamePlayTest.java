import org.example.api.AIPlayer;
import org.example.api.GameEngine;
import org.example.api.RuleEngine;
import org.example.boards.TicTacBoard;
import org.example.game.*;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GamePlayTest {
    private final GameEngine gameEngine = new GameEngine();
    private final RuleEngine ruleEngine = new RuleEngine();

    public GameState play(int[][] playerMoves, int[][] aiMoves, Board board) {
        if(board instanceof TicTacBoard) {
            Player user = new Player("X");
            Player computer = new AIPlayer("O");
            int totalMoves = Math.max(playerMoves.length, aiMoves.length);
            for (int i = 0; i < totalMoves; i++) {
                if (i < playerMoves.length && !ruleEngine.getState(board).isGameOver()) {
                    int[] move = playerMoves[i];
                    gameEngine.move(board, new Move(user, new Cell(move[0], move[1])));
                }
                System.out.println(board);
                if (i < aiMoves.length && !ruleEngine.getState(board).isGameOver()) {
                    int[] move = aiMoves[i];
                    gameEngine.move(board, new Move(computer, new Cell(move[0], move[1])));
                }
            }
            return ruleEngine.getState(board);
        }else throw new IllegalArgumentException();
    }


    @Test
    public void checkRowWin() {
        int[][] playerMoves = {{2, 0}, {2, 1}, {2, 2}};
        int[][] aiMoves = {{0, 0}, {0, 1}, {0, 2}};
        Board board = gameEngine.start("TicTacBoard");
        GameState state = play(playerMoves, aiMoves, board);
        assertEquals("X", state.getWinner());
        assertTrue(state.isGameOver());
    }

    @Test
    public void checkColWin() {
        int[][] playerMoves = {{0, 1}, {1, 1}, {2, 1}};
        int[][] aiMoves = {{0, 0}, {1, 0}, {2, 0}};
        Board board = gameEngine.start("TicTacBoard");
        GameState state = play(playerMoves, aiMoves, board);
        assertEquals("X", state.getWinner());
        assertTrue(state.isGameOver());
    }

    @Test
    public void checkDiag1Win() {
        int[][] playerMoves = {{0, 0}, {1, 1}, {2, 2}};
        int[][] aiMoves = {{2, 0}, {2, 1}, {1, 2}};
        Board board = gameEngine.start("TicTacBoard");
        GameState state = play(playerMoves, aiMoves, board);
        assertEquals("X", state.getWinner());
        assertTrue(state.isGameOver());
    }

    @Test
    public void checkDiag2Win() {
        int[][] playerMoves = {{0, 2}, {1, 1}, {2, 0}};
        int[][] aiMoves = {{0, 1}, {2, 1}, {2, 2}};
        Board board = gameEngine.start("TicTacBoard");
        GameState state = play(playerMoves, aiMoves, board);
        assertEquals("X", state.getWinner());
        assertTrue(state.isGameOver());
    }

    @Test
    public void checkDraw() {
        int[][] playerMoves = {{0, 0}, {0, 1}, {1, 2}, {2, 0}, {2, 2}};
        int[][] aiMoves = {{0, 2}, {1, 0}, {1, 1}, {2, 1}};
        Board board = gameEngine.start("TicTacBoard");
        GameState state = play(playerMoves, aiMoves, board);
        assertEquals("-", state.getWinner());
        assertTrue(state.isGameOver());
    }

    @Test
    public void checkFork() {
        int[][] playerMoves = {{0, 0}, {1, 1}, {1, 0}};
        int[][] aiMoves = {{0, 1}, {2, 1}};
        Board board = gameEngine.start("TicTacBoard");
        GameInfo info = ruleEngine.getInfo(board);
        assertEquals("-", info.getGameState().getWinner());
        assertTrue(info.isFork());
    }

}