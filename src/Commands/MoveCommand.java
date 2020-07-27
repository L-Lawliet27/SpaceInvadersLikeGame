package Commands;

import Exceptions.OffWorldException;
import Game.Game;

public class MoveCommand extends Command
{
	
	protected String[] outWords;
	
	protected final String right = "right";
	protected final String left = "left";
	protected final String rightShort = "r";
	protected final String leftShort = "l";
	protected final String amount1 = "1";
	protected final String amount2 = "2";
	private String direction;
	private int cells;

	
	//words 
	
	public MoveCommand() 
	{
		super("move", "m", "Move", "Causes the UCM-Ship to move as indicated");
	}

	public MoveCommand(String direction, int cells){
		super("move", "m", "Move", "Causes the UCM-Ship to move as indicated");

		this.direction = direction;
		this.cells = cells;
	}


	@Override
	public boolean execute(Game game) throws OffWorldException {

		if(direction.equals(right) || direction.equals(rightShort)){
			game.move(cells);
		} else if (direction.equals(left) || direction.equals(leftShort)){
			game.move(-cells);
		}
		return true;
	}

	@Override
	public Command parse(String[] words) {

		if (matchCommandName(words[0]) && commandExtComp(words[1]) && commandNumComp(words[2]))
		{
			String direction = words[1].toLowerCase();
			int cells = Integer.parseInt(words[2]);

			return new MoveCommand(direction,cells);
		}
		
		else return null;
	}



	protected boolean commandExtComp(String words)
	{
		return right.equalsIgnoreCase(words) || left.equalsIgnoreCase(words) 
				|| rightShort.equalsIgnoreCase(words) || leftShort.equalsIgnoreCase(words);
		
	}//Command Compares second command
	
	
	protected boolean commandNumComp(String words)
	{
		return amount1.equals(words) || amount2.equals(words);
		
	}// Command Compares third command
	


}
