package Commands;

import Exceptions.*;
import Game.Game;

import java.io.*;
import java.util.Scanner;

public class LoadCommand extends Command{

    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private Scanner nameOfFile;
    private String lineRead;

    public LoadCommand() {
        super("load", "ld", "Load", "Loads Saved Game File");
        nameOfFile = new Scanner(System.in);
    }

    @Override
    public boolean execute(Game game) throws FileContentsException, IOException {
        lineRead = "";
//        System.out.println("Type Desired Name of Loaded Match: ");
        String name = nameOfFile.nextLine();
        name = name + ".dat";

        try{
            bufferedReader = new BufferedReader(new FileReader(name));
            lineRead = bufferedReader.readLine();
            lineRead = lineRead + bufferedReader.readLine();
            game.load(bufferedReader);


        } catch (IOException e){
            System.err.println("Complications in Loading Game" + "%n%n");
        }

        System.out.println(lineRead);
        System.out.println("Game successfully loaded from file " + name + "\n");
        return true;
    }

    @Override
    public Command parse(String[] words) {

        if (matchCommandName(words[0])) return new LoadCommand();

        else return null;
    }
}
