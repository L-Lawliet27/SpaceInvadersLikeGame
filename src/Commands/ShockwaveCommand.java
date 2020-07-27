package Commands;


import Exceptions.NoShockwaveException;
import Game.Game;

public class ShockwaveCommand extends Command {

	public ShockwaveCommand() {
		super("shockwave", "w", "Shockwave",
				"Causes the UCM-Ship to release a shock wave");
	}


	@Override
	public boolean execute(Game game) throws NoShockwaveException {

		return game.shockWave();
	}

	@Override
	public Command parse(String[] words)
	{
		if (matchCommandName(words[0])) return new ShockwaveCommand();
		
		else return null;
	}
	

}
