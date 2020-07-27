package GameElements.Ships.Enemies;

import Game.Game;
import GameElements.GameElement;
import GameElements.Ships.UCMShip;
import Utils.FileContentsVerifier;
import Utils.IExecuteRandomActions;

public class CarrierShip extends AlienShip{

    public CarrierShip(Game game, int dimX, int dimY) {
        super(game, dimX, dimY, 2, 5);
    }

    public CarrierShip() {}
    public CarrierShip(Game game, int dimX, int dimY, int shield, boolean dir) {
        super(game, dimX, dimY, shield, 5);
        rightToLeft = dir;
    }
    @Override
    public String stringify() {
        serial[0] = "C;";
        serial[1] = initCord[0] + "," + initCord[1] + ";";
        serial[2] = getShield() + ";";
        serial[3] = game.movingCycles() + ";";
        if(getDir()) serial[4] = "left;";
        else serial[4] = "right";

        return serial[0]+serial[1]+serial[2]+serial[3]+serial[4];
    }

    @Override
    public void computerAction() {
        if(IExecuteRandomActions.canTurnExplosive(game)){
            game.replaceElement(this,new ExplosiveShip(game, initCord[0], initCord[1]));
            reduceForces();
        }
    }


    @Override
    public String toString() {
        return "-<" + shield + ">-";
    }
}
