package Commands;

import Game.Game;

public class HelpCommand extends Command {
	
	
	public HelpCommand() 
	{
		super("Help", "H", "Help", "Prints this help message");
	}


	@Override
	public boolean execute(Game game) {
		System.out.println(CommandGenerator.commandHelp());
		return false;
	}

	@Override
	public Command parse(String[] words) 
	{
		if (matchCommandName(words[0])) return new HelpCommand();
		
		else return null;

	}

}
