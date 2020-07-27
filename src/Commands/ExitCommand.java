package Commands;

import Game.Game;

public class ExitCommand extends Command {
	

	public ExitCommand() {
		super("exit", "e", "Exit", "Terminates the program");
	}


	@Override
	public boolean execute(Game game) {
		game.exit();
		game.getWinnerMessage();
		return true;
	}

	@Override
	public Command parse(String[] words) 
	{
		if (matchCommandName(words[0])) return new ExitCommand();
		
		else return null;
	}

}
