package Commands;


import Game.Game;

public class UpdateCommand extends Command {

	
	public UpdateCommand() {
		
		super("update", "", "Update", "This command is used by the system");
		
	}


	@Override
	public boolean execute(Game game) {
		game.update();
		return true;
	}

	@Override
	public Command parse(String[] words) {

		if (matchCommandName(words[0])) return new UpdateCommand();

		else return null;
	}

}
