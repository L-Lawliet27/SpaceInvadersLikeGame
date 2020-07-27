package Commands;


import Game.Game;
import Print.PrinterTypes;

public class ListPrintersCommand extends Command {


	public ListPrintersCommand() {
		
		super("listPrinters", "lp", "List Printers",
				"Displays the list of printers available");
	}


	@Override
	public boolean execute(Game game) {
		System.out.println(PrinterTypes.printerHelp(game));
		return false;
	}

	@Override
	public Command parse(String[] words) 
	{
		if (matchCommandName(words[0])) return new ListPrintersCommand();
		
		else return null;
	}


}
