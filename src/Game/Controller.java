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

    public void run() throws FileContentsException, SuperMissileException, IOException, NoShockwaveException, MissileInFlightException, OffWorldException {
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

            } catch (CommandParseException | CommandExecuteException ex){
                System.err.format(ex.getMessage() + "%n%n");
                Throwable cause = ex.getCause();
                if(cause != null){
                    System.err.println("Cause of Exception:");
                    System.err.println(" " + ex.getCause());
                }
            }
        }
        System.out.println(game.getWinnerMessage());
        ;


    }//run



}// class GM.Controller



