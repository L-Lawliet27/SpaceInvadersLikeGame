package GameElements.Ships;

import Game.Game;
import GameElements.GameElement;

public abstract class Ship extends GameElement {

    public Ship(Game game, int dimX, int dimY, int lives) {
        super(game, dimX, dimY, lives);
    }


    public Ship() {
        super();
    }

}
