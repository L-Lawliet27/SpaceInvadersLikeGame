package Commands;


import Exceptions.CommandExecuteException;
import Exceptions.NoShockwaveException;
import Game.Game;

public class ShockwaveCommand extends Command {

	public ShockwaveCommand() {
		super("shockwave", "w", "Shockwave",
				"Causes the UCM-Ship to release a shock wave");
	}


	@Override
	public boolean execute(Game game) {

		try {
			if (!game.shockWave()) throw new CommandExecuteException("Failed to Shoot");
		}catch (CommandExecuteException e){
			System.err.println(e.getMessage());
		}

		return true;
	}

	@Override
	public Command parse(String[] words)
	{
		if (matchCommandName(words[0])) return new ShockwaveCommand();
		
		else return null;
	}
	

}
