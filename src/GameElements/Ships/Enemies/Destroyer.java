package GameElements.Ships.Enemies;

import Game.Game;
import GameElements.Weapons.Bomb;
import Utils.IExecuteRandomActions;

public class Destroyer extends AlienShip implements IExecuteRandomActions {

    private boolean dropBomb;
    private static int currentSerialNumber;

    public Destroyer(Game game, int dimX, int dimY) {
        super(game, dimX, dimY, 1,10);
        dropBomb = true;
    }

    public Destroyer() {
    }

    public Destroyer(Game game, int dimX, int dimY, int shield, boolean dir) {
        super(game, dimX, dimY, shield, 5);
        rightToLeft = dir;
    }

    private void initialiseLabelling () {
        currentSerialNumber = 1;
    }

    private String generateStringifyLabel() {
        label = currentSerialNumber;
        currentSerialNumber++;
        return labelRefSeparator + label;
    }


    @Override
    public String stringify() {
        if (!game.isStringifying() ) {
            game.setStringifying() ;
            initialiseLabelling() ;
        }
        serial[0] = "D;";
        serial[1] = initCord[0] + "," + initCord[1] + ";";
        serial[2] = getShield() + ";";
        serial[3] = game.movingCycles() + ";";
        if(getDir()) serial[4] = "left;";
        else serial[4] = "right";

        return serial[0]+serial[1]+serial[2]+serial[3]+serial[4] + generateStringifyLabel();
    }

    public void setDropBomb(){
        dropBomb = !dropBomb;
    }

    @Override
    public void computerAction() {
        if(dropBomb && IExecuteRandomActions.canGenerateBomb(game)){
            //TODO
            game.addObject(new Bomb(game, initCord[0], initCord[1], this));
            setDropBomb();
        }
    }


    @Override
    public String toString() {
        return "!<" + shield + ">!";
    }


    public boolean isOwner(int ref) {
        boolean itsMe = label==ref;
        if (itsMe) dropBomb = false;
        return itsMe;
    }
}
