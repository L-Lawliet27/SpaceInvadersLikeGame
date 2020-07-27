package Commands;


import Game.Game;

public class ResetCommand extends Command {

	
	public ResetCommand() {
		super("reset", "r", "Reset", "Starts a new game");
	}


	@Override
	public boolean execute(Game game) {
		game.reset();
		return true;
	}

	@Override
	public Command parse(String[] words)
	{
		if (matchCommandName(words[0])) return new ResetCommand();
		
		else return null;
	}
	

}
