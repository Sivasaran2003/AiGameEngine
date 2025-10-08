package org.example;

import org.example.api.*;
import org.example.boards.BoardProxy;
import org.example.boards.TicTacBoard;
import org.example.commands.SendEmailCommand;
import org.example.commands.SendSMSCommand;
import org.example.commands.builders.SendEmailCommandBuilder;
import org.example.commands.builders.SendSMSCommandBuilder;
import org.example.game.*;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        Board board = gameEngine.start("TicTacBoard");
        RuleEngine ruleEngine = new RuleEngine();
        Game game = GameCreator.createGame();
        EmailService emailService = new EmailService();
        SMSService smsService = new SMSService();

        int row, col;
        Scanner scanner = new Scanner(System.in);

        while(!ruleEngine.getState(board).isGameOver()) {
            AIPlayer computer = new AIPlayer("O");
            Player human = new Player("X");

            if(human.getUser().activeAfter(10, TimeUnit.DAYS)) {
                emailService.execute( new SendEmailCommandBuilder()
                                .user(human.getUser())
                                .link("www.tic-tac-toe.com")
                                .message("Welcome back")
                        .build());
                smsService.execute(new SendSMSCommandBuilder()
                        .user(human.getUser())
                        .message("Welcome back")
                        .build());
            }

            System.out.println("Make your move !!");
            row = scanner.nextInt();
            col = scanner.nextInt();
            Move oppMove = new Move(human, new Cell(row, col));
            board = gameEngine.move(board, oppMove);

            if(!ruleEngine.getState(board).isGameOver()) {
                Move compMove = computer.suggestMove(board, computer);
                board = gameEngine.move(board, compMove);
                System.out.println("Computer move : " + compMove);
            }

            System.out.println(board);
        }

        System.out.println(ruleEngine.getState(board).getWinner() + " is the winner ");
        TicTacBoard ticTacBoard = (TicTacBoard) board;

        emailService.execute(new SendEmailCommandBuilder().
                user(ruleEngine.getState(board).getWinner().getUser())
                .message("Congrats on the win")
                .user(ruleEngine.getState(board).getWinner().getUser())
                .build());
        smsService.execute(new SendSMSCommandBuilder()
                .user(ruleEngine.getState(board).getWinner().getUser())
                .message("Congrats on the win")
                .build());

        for(BoardProxy proxy : gameEngine.getHistory().getBoards()) {
            System.out.println(proxy);
        }
    }
}