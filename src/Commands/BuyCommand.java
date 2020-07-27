package Commands;


import Exceptions.SuperMissileException;
import Game.Game;

public class BuyCommand extends Command{
	
	protected final String spMissile;

	public BuyCommand() 
	{
		super("buy","b","Buy","Allows you to buy a supermissile if you have 20 points");
		
		this.spMissile = "supermissile";
	}

//	@Override
//	public boolean execute(Game game) {
//		System.out.println("buy");
//		return true;
//	}

	@Override
	public boolean execute(Game game) throws SuperMissileException {

		if(game.currentPoints() >= 20){
			game.receivePoints(-20);
			game.setSuperMissile();
			System.out.println("Super Missile Purchased!! \n");
			game.update();
		} else throw new SuperMissileException("Not Enough Points to Purchase SuperMissile" + "%n%n");
		return true;
	}

	@Override
	public Command parse(String[] words) {
		
		if (commandComp(words[0]) && commandComp(words[1])) return new BuyCommand();

		else return null;
	}

	
	protected boolean commandComp(String words) 
	{
		return name.equalsIgnoreCase(words) || spMissile.equalsIgnoreCase(words);
		
	}//Command Compare
	

}
