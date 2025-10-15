package org.example;

import org.example.api.*;
import org.example.boards.BoardProxy;
import org.example.commands.SendEmailCommand;
import org.example.commands.SendSMSCommand;
import org.example.events.*;
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
        EventBus eventBus = new EventBus();
        AIPlayer computer = new AIPlayer("O");
        Player human = new Player("X");

        eventBus.subscribe(new Subscriber(event -> emailService.execute(new SendEmailCommand(event))));
        eventBus.subscribe(new Subscriber(event -> smsService.execute(new SendSMSCommand(event))));

        int row, col;
        Scanner scanner = new Scanner(System.in);

        while(!ruleEngine.getState(board).isGameOver()) {

            if(human.getUser().activeAfter(10, TimeUnit.DAYS)) {
                eventBus.publish(new ActivityEvent(human.getUser()));
            }

            System.out.println("Make your move !!");
            row = scanner.nextInt();
            col = scanner.nextInt();
            Move oppMove = new Move(human, Cell.getCell(row, col));
            board = gameEngine.move(board, oppMove);

            if(!ruleEngine.getState(board).isGameOver()) {
                Move compMove = computer.suggestMove(board, computer);
                board = gameEngine.move(board, compMove);
                System.out.println("Computer move : " + compMove);
            }

            System.out.println(board);
        }

        System.out.println(ruleEngine.getState(board).getWinner() + " is the winner ");

        eventBus.publish(new WinEvent(ruleEngine.getState(board).getWinner().getUser()));

        for(BoardProxy proxy : gameEngine.getHistory().getBoards()) {
            System.out.println(proxy);
        }
    }
}