package GameElements.Ships.Enemies;

import Game.Game;
import GameElements.GameElement;
import GameElements.Ships.UCMShip;
import Utils.FileContentsVerifier;
import Utils.IExecuteRandomActions;

public class UFO extends EnemyShip implements IExecuteRandomActions {

    private boolean onScreen;

    public UFO(Game game, int dimX, int dimY) {
        super(game, dimX, dimY, 1, 25);
        serial = new String[3];
    }

    public UFO(Game game, int dimX, int dimY, int shield) {
        super(game, dimX, dimY, shield, 25);
        serial = new String[3];
    }

    public UFO() {
        super();
    }

    @Override
    public String stringify() {

        serial[0] = "U;";
        serial[1] = initCord[0] + "," + initCord[1] + ";";
        serial[2] = getShield() + "";

        return serial[0]+serial[1]+serial[2];
    }

    @Override
    public GameElement parse(String inLine, Game game, FileContentsVerifier verifier) {
        line = inLine.split(";");
        coord = line[1].split(",");

        if(line[0].equals("U")) {
            if (verifier.verifyUfoString(inLine, game, Integer.parseInt(line[2]))) {
                return new UFO(game, Integer.parseInt(coord[0]), Integer.parseInt(coord[1]),
                        Integer.parseInt(line[2]));
            }
        }

        return null;
    }


    @Override
    public void computerAction() {
        if(!isOut() && IExecuteRandomActions.canGenerateUfo(game)){
            // TODO: 7/7/20
            onScreen = true;
        }
    }

    @Override
    public void move() {
        // TODO: 7/7/20
        if(onScreen){
            initCord[0]--;
            if(initCord[0] == -1){
                reset();
            }
        }

    }

    @Override
    public void onDelete() {
       if(shield == 0) {
           game.enableShockWave();
           game.receivePoints(pointValue);
           reset();
       }
    }

    @Override
    public String toString() {
        return "<(+)>";
    }


    private void reset(){
        onScreen = false;
        initCord[0] = Game.DIM_Y;
        shield = 1;
    }

}
