package Commands;

import Exceptions.*;
import Game.Game;

import java.io.IOException;

public abstract class Command {
    protected final String name;
    protected final String shortCut;
    private final String details ;
    private final String help;
    protected static final String incorrectNumArgsMsg = "Incorrect number of arguments";
    protected static final String incorrectArgsMsg = "Incorrect argument format";

    public Command(String name, String shortCut, String details, String help){
        this.name = name;
        this.shortCut = shortCut;
        this.details = details;
        this.help = help;
    }
    public abstract boolean execute(Game game) throws CommandExecuteException, SuperMissileException, NoShockwaveException, OffWorldException, MissileInFlightException, IOException, FileContentsException;

    public abstract Command parse(String[] commandWords);

    protected boolean matchCommandName(String name) {
        return this.shortCut.equalsIgnoreCase(name) ||
                this.name.equalsIgnoreCase(name);
    }
    public String helpText(){
        return details + " : " + help + "\n";
    }

    public String getName(){
        return name;
    }
}