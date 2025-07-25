package org.example;

import org.example.api.AIPlayer;
import org.example.api.GameEngine;
import org.example.api.RuleEngine;
import org.example.game.Board;
import org.example.game.Cell;
import org.example.game.Move;
import org.example.game.Player;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        Board board = gameEngine.start("TicTacBoard");
        RuleEngine ruleEngine = new RuleEngine();

        int row, col;
        Scanner scanner = new Scanner(System.in);

        while(!ruleEngine.getState(board).isGameOver()) {
            AIPlayer computer = new AIPlayer("O");
            Player user = new Player("X");
            System.out.println("Make your move !!");
            row = scanner.nextInt();
            col = scanner.nextInt();
            Move oppMove = new Move(user, new Cell(row, col));
            gameEngine.move(board, oppMove);

            if(!ruleEngine.getState(board).isGameOver()) {
                Move compMove = computer.suggestMove(board, computer);
                gameEngine.move(board, compMove);
                System.out.println("Computer move : " + compMove);
            }

            System.out.println(board);
        }

        System.out.println(ruleEngine.getState(board).getWinner() + " is the winner ");
    }
}






















