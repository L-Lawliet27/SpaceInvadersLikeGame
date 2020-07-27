package Commands;

import Game.Game;
import Print.GamePrinter;
import Print.PrinterTypes;

public class StringifyCommand extends Command {

    public StringifyCommand() {
        super("stringify", "str", "Stringify", "Sends stringified state of" +
                "the game as output to the screen");
    }

    @Override
    public boolean execute(Game game) {
        GamePrinter gamePrinter = PrinterTypes.STRINGIFIER.getObject(game);
        System.out.println(gamePrinter);
        return false;
    }

    @Override
    public Command parse(String[] words) {
        if(matchCommandName(words[0])) return new StringifyCommand();
        else return null;
    }
}
