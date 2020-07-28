package Commands;

import Exceptions.*;
import Game.Game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class SaveCommand extends Command{

    private Scanner nameOfFile;
//    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;

    public SaveCommand() {
        super("save", "sv", "Save", "Saves State of the Game");
        nameOfFile = new Scanner(System.in);
    }

    @Override
    public boolean execute(Game game) throws CommandExecuteException, SuperMissileException, NoShockwaveException, OffWorldException, MissileInFlightException, IOException {

//        System.out.println("Type Desired Name of Saved Match: ");
        String name = nameOfFile.nextLine();
        name = name + ".dat";
        System.out.println("\n");

        try{
            bufferedWriter = new BufferedWriter(new FileWriter(name));
            bufferedWriter.write(game.stringify());
            bufferedWriter.close();

        } catch (IOException e) {
           throw new IOException("Complications in Saving Game" + "%n%n");
        }

        System.out.println("Game successfully saved in file " + name +"\n Use the load command to reload it \n\n");

        return false;
    }

    @Override
    public Command parse(String[] words)
    {
        if (matchCommandName(words[0])) return new SaveCommand();

        else return null;
    }
}
