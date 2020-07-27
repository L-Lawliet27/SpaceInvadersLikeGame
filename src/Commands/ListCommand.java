package Commands;


import Game.Game;

public class ListCommand extends Command {
	
	
	public ListCommand() {
		
		super("list", "l", "List", "Displays the list of ship types in the game");
	}


	@Override
	public boolean execute(Game game) {
		System.out.println("-<x>-: Points: 5 - Damage: 0 - Resistance: 2");
		System.out.println("*<n>*: Points: 5 - Damage: 0 - Resistance: 2");
		System.out.println("!<x>!: Points: 10 - Damage: 1 - Resistance: 1");
		System.out.println("<(+)>: Points: 25 - Damage: 0 - Resistance: 1");
		System.out.println("/-^-\\: Damage: 1 - Resistance: 3");
		return false;
	}

	@Override
	public Command parse(String[] words) 
	{
		if (matchCommandName(words[0])) return new ListCommand();
		
		else return null;
	}


}
