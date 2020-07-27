package Commands;

import Exceptions.MissileInFlightException;
import Exceptions.SuperMissileException;
import Game.Game;

public class ShootCommand extends Command
{
	protected final String spMissile;
	protected final String spMissileShortCut;
	private boolean sp;
	
	public ShootCommand() 
	{
		super("shoot", "s", "Shoot", "Causes the UCM-Ship to launch a missile or a SuperMissile");
		this.spMissile = "supermissile";
		this.spMissileShortCut = "sm";
		sp = false;
	}

	public ShootCommand(boolean sp)
	{
		super("shoot", "s", "Shoot", "Causes the UCM-Ship to launch a missile or a SuperMissile");
		this.spMissile = "supermissile";
		this.spMissileShortCut = "sm";
		this.sp = sp;
	}
	
	
//	public void setM(Boolean n)
//	{
//		this.sp = true;
//	}


	@Override
	public boolean execute(Game game) throws MissileInFlightException {

		if(game.statSuperMissile() && sp) {

			return game.shootSuperMissile();
		}

		return game.shootMissile();

	}


	@Override
	public Command parse(String[] words) 
	{
		if (words.length == 2 && matchCommandName(words[0]) && matchCommandName(words[1]))
		{
			return new ShootCommand(true);

		} else if(words.length == 1 && matchCommandName(words[0]) ){
			return new ShootCommand();
		} else return null;
	}

	@Override
	protected boolean matchCommandName(String name) {
		return this.shortCut.equalsIgnoreCase(name) ||
				this.name.equalsIgnoreCase(name) || this.spMissile.equals(name) ||
				this.spMissileShortCut.equals(name);
	}




}

