package org.example;

public class GameEngine {
    public Board start() {
        return new Board();
    }

    public void move(Board board, Player player, Move move) {

    }

    public GameResult isComplete(Board board) {
        if (board instanceof TicTacBoard) {
            boolean rowComplete = false, colComplete = false, diagComplete = true, revDiagComplete = true;
            TicTacBoard ticTacBoard = (TicTacBoard) board;
            String firstChar = "";
            for (int i = 0; i < 3; i++) {
                rowComplete = true;
                firstChar = ticTacBoard.cells[i][0];
                for (int j = 0; j < 3; j++) {
                    if (!ticTacBoard.cells[i][j].equals(firstChar)) {
                        rowComplete = false;
                        break;
                    }
                }
            }

            if (rowComplete) return new GameResult(true, firstChar);

            for (int i = 0; i < 3; i++) {
                colComplete = true;
                firstChar = ticTacBoard.cells[0][i];
                for (int j = 0; j < 3; j++) {
                    if (!ticTacBoard.cells[j][i].equals(firstChar)) {
                        colComplete = false;
                        break;
                    }
                }
            }

            if (colComplete) return new GameResult(true, firstChar);

            firstChar = ticTacBoard.cells[0][0];
            for (int i = 0; i < 3; i++) {
                if (!ticTacBoard.cells[i][i].equals(firstChar)) {
                    diagComplete = false;
                    break;
                }
            }

            if (diagComplete) return new GameResult(true, firstChar);

            firstChar = ticTacBoard.cells[0][2];
            for (int i = 0; i < 3; i++) {
                if (!ticTacBoard.cells[i][2 - i].equals(firstChar)) {
                    revDiagComplete = false;
                    break;
                }
            }

            if (revDiagComplete) return new GameResult(true, firstChar);

            int countFilledCells = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (ticTacBoard.cells[i][j] != null) countFilledCells++;
                }
            }

            if (countFilledCells != 9) {
                return new GameResult(false, "-");
            }

            return new GameResult(true, "-");
        }

        return new GameResult(false, "-");
    }
}
