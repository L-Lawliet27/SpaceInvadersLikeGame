package Game;

import Commands.CommandGenerator;
import Commands.Command;
import Print.GamePrinter;
import Print.PrinterTypes;
import Exceptions.*;

import java.io.IOException;
import java.util.Scanner;

public class Controller
{
    private Scanner input = new Scanner(System.in);
    private Game game;
    private GamePrinter gamePrinter;

    public Controller(Game game){
        this.game = game;
        gamePrinter = PrinterTypes.BOARDPRINTER.getObject(game);
    }

    public void run()
    {
        System.out.println(gamePrinter);
        while (!game.isFinished()){
            System.out.println("Command > ");
            String[] words = input.nextLine().toLowerCase().trim().split ("\\s+");
            try {
                Command command = CommandGenerator.parse(words);
                if (command != null) {
                    if (command.execute(game))
                        System.out.println(gamePrinter);

                } else throw new CommandParseException("Invalid Command");

            } catch (CommandParseException | CommandExecuteException | SuperMissileException | NoShockwaveException | OffWorldException | MissileInFlightException | IOException | FileContentsException ex){
                System.err.format(ex.getMessage() + "%n%n");
            }
        }
        System.out.println(game.getWinnerMessage());
        ;


    }//run

//        boolean exit = false;
//
//        while (!exit) {
//
//
//            System.out.println();
//
//            String[] words = input.nextLine().toLowerCase().trim().split("\\s+");
//
//            Command com = CommandGenerator.parse(words);
//
//            if (com != null)
//            {
//                if(com.getName().equals("exit")) exit = true;
//
//                else if (com.execute(game)) System.out.println(game);
//
//                else System.out.format();
//            }
//
//        }// while
//
//        System.out.println("GM.Game Exited");





}// class GM.Controller



