package Print;

import Game.Game;

public class Stringifier extends GamePrinter{

    public Stringifier() {
    }

    @Override
    public String toString() {
        return printerGame.stringify();
    }
}
