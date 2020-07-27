package Game;

import Exceptions.LevelChosenException;

import java.util.Scanner;

public class ChoosingLevel {

    private Level lv;
    private Scanner l;
    private String args;

    public ChoosingLevel(String args){
        this.args = args.toUpperCase();
    }

    public ChoosingLevel() {
    }

    Level select() {
        if (this.args == null) {
            try {
                l = new Scanner(System.in);
                System.out.println("Choose Level: Easy, Hard, Insane");
                String[] ls = l.nextLine().toUpperCase().split("\\s+");
                switch (ls[0]) {
                    case "EASY":
                        lv = Level.EASY;
                        break;
                    case "HARD":
                        lv = Level.HARD;
                        break;
                    case "INSANE":
                        lv = Level.INSANE;
                        break;
                    default: throw new LevelChosenException("Invalid Level - Usage: Main <EASY|HARD|INSANE> [seed]:" +
                            " level must be one of: EASY, HARD, INSANE");
                }
            } catch (LevelChosenException | RuntimeException e){
                System.err.format(e.getMessage() + "%n%n");
            }

        } else {
            try {
                switch (args) {
                    case "EASY":
                        lv = Level.EASY;
                        break;
                    case "HARD":
                        lv = Level.HARD;
                        break;
                    case "INSANE":
                        lv = Level.INSANE;
                        break;
                    default:
                        throw new LevelChosenException("Usage: Main <EASY|HARD|INSANE> [seed]:" +
                                " level must be one of: EASY, HARD, INSANE");
                }
            } catch (LevelChosenException | RuntimeException e){
                System.err.format(e.getMessage() + "%n%n");
            }

        }

        return lv;
    }

}
