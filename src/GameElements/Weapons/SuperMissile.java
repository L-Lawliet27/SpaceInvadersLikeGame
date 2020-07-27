package GameElements.Weapons;

import Game.Game;

public class SuperMissile extends Missile{

    public SuperMissile(Game game, int dimX, int dimY) {
        super(game, dimX, dimY, 2);
    }

    public SuperMissile() {}

    @Override
    public String toString() {
        return "/s\\";
    }

    @Override
    public String stringify() {
        serial[0] = "X;";
        serial[1] = initCord[0] + "," + initCord[1] + "";

        return serial[0] + serial[1];
    }
}
