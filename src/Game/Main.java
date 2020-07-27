package Game;

import Exceptions.CommandLineParametersException;

import java.util.Random;

public class Main {

    public static void main (String[] args) throws CommandLineParametersException, RuntimeException {
        Random r = new Random();
        Game game;
        if(args.length < 3) {
            if (args.length == 2) {
                try {
                    r = new Random(Integer.parseInt(args[1]));
                    game = new Game(new ChoosingLevel(args[0]).select(), r);
                    new Controller(game).run();
                } catch (RuntimeException e){
                    System.err.format("Usage: Main <EASY|HARD|INSANE> [seed]: the seed must be a number" + "%n%n");
                }

            } else if (args.length == 1) {
                try {
                    game = new Game(new ChoosingLevel(args[0]).select(), r);
                    new Controller(game).run();
                } catch (NullPointerException e){
                    System.err.format("Usage: Main <EASY|HARD|INSANE> [seed]" + "%n%n");
                }

            } else {
                game = new Game(new ChoosingLevel().select(), r);
                new Controller(game).run();
            }

        } else throw new CommandLineParametersException("Usage: Main <EASY|HARD|INSANE> [seed]" + "%n%n");
    }

}
