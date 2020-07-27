package Print;

import Game.Game;

public abstract class GamePrinter {
    protected Game printerGame;

    public GamePrinter() {}

    public abstract String toString();

    public void setGame(Game game){
        printerGame = game;
    }
}
