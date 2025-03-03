package Commands;

public class CommandGenerator
{

    private static final Command[] availableCommands =
            {
                    new ListCommand(),
                    new HelpCommand(),
                    new ResetCommand(),
                    new ExitCommand(),
                    new UpdateCommand(),
                    new ShootCommand(),
                    new ShockwaveCommand(),
                    new MoveCommand(),
                    new BuyCommand(),
                    new StringifyCommand(),
                    new SaveCommand(),
                    new LoadCommand(),
                    new ListPrintersCommand()
            };


    public static Command parse (String[] words)
    {

        for (Command cmd : availableCommands)
        {
            if(cmd.parse(words) != null){
                return cmd.parse(words);
            }

//            if(cmd.matchCommandName(words[0])) return cmd.parse(words);
        }

        return null;

    } //parse


    public static String commandHelp()
    {
        String hp = "";
        for (Command i : availableCommands)
        {
            hp = hp + i.helpText() + "\n";
        }

        return hp;
    }


}//Command Generator
