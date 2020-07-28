package Game;

import Exceptions.CommandLineParametersException;

import java.util.Random;

public class Main {

    public static void main (String[] args) throws CommandLineParametersException, RuntimeException {
        Random r = new Random();
        Game game;

        if(args.length < 3) {

            Level lv = null;

            try {
                lv = Level.parse(args[0]);
            } catch(NullPointerException e){
                System.err.format("Incorrect mode. Choose EASY, HARD or INSANE " + "%n%n");
            }

            if (args.length == 2) {
                try {
                    r = new Random(Integer.parseInt(args[1]));
                    game = new Game(lv, r);
                    new Controller(game).run();
                } catch (RuntimeException e){
                    System.err.format("Usage: Main <EASY|HARD|INSANE> [seed]: the seed must be a number" + "%n%n");
                }

            } else {
                try {
                    game = new Game(lv, r);
                    new Controller(game).run();
                } catch (NullPointerException e){
                    System.err.format("Usage: Main <EASY|HARD|INSANE> [seed]" + "%n%n");
                }

            }

        } else throw new CommandLineParametersException("Usage: Main <EASY|HARD|INSANE> [seed]" + "%n%n");
    }

}
