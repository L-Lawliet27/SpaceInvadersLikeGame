package GameElements.Ships.Enemies;

import Game.Game;
import GameElements.GameElement;

public class ExplosiveShip extends CarrierShip{

    private final int damage = 1;
    private boolean kamikaze;

    public ExplosiveShip(Game game, int dimX, int dimY) {
        super(game, dimX, dimY);
        kamikaze = false;
    }

    public ExplosiveShip(Game game, int dimX, int dimY, int shield, boolean dir) {
        super(game, dimX, dimY, shield, dir);
        rightToLeft = dir;
        kamikaze = false;
    }

    public ExplosiveShip() {}

    @Override
    public String stringify() {
        serial[0] = "E;";
        serial[1] = initCord[0] + "," + initCord[1] + ";";
        serial[2] = getShield() + ";";
        serial[3] = game.movingCycles() + ";";
        if(getDir()) serial[4] = "left;";
        else serial[4] = "right";

        return serial[0]+serial[1]+serial[2]+serial[3]+serial[4];
    }

    @Override
    public boolean performAttack(GameElement other) {
        if(kamikaze){
            int x = other.getCord('x');
            int y = other.getCord('y');

            if(game.isOnBoard(initCord[0]-1,initCord[1]-1)){
                for (int i = initCord[0] - 1; i <= initCord[0] + 1; i++) {
                    for (int j = initCord[1] - 1; j <= initCord[1] + 1; j++) {
                        if(x == i && y == j){
                            other.receiveMissileAttack(damage);
                            //since it accomplished the same task as the missile, only that
                            //instead of affecting one, it affects only the ones around it,
                            //so it cannot be the shockwave
                        }
                    }
                }
            } else if(initCord[0] - 1 == -1){
                for (int i = 0; i <= initCord[0] + 2; i++) {
                    for (int j = initCord[1] - 1; j <= initCord[1] + 1; j++) {
                        if(x == i && y == j){
                            other.receiveMissileAttack(damage);
                        }
                    }

                }
            } else if (initCord[1] - 1 == -1){
                for (int i = initCord[0] - 1; i <= initCord[0] + 1; i++) {
                    for (int j = 0; j <= initCord[1] + 2; j++) {
                        if(x == i && y == j){
                            other.receiveMissileAttack(damage);
                        }
                    }

                }
            } else if (initCord[0] + 1 == Game.DIM_X && initCord[1] + 1 != Game.DIM_Y){
                for (int i = initCord[0]-1; i <= initCord[0] ; i++) {
                    for (int j = initCord[1] - 1; j <= initCord[1] + 1; j++) {
                        if(x == i && y == j){
                            other.receiveMissileAttack(damage);
                        }
                    }
                }
            } else if(initCord[0] + 1 != Game.DIM_X && initCord[1] + 1 == Game.DIM_Y){
                for (int i = initCord[0]-1; i <= initCord[0] +1 ; i++) {
                    for (int j = initCord[1] - 1; j <= initCord[1]; j++) {
                        if(x == i && y == j){
                            other.receiveMissileAttack(damage);
                        }
                    }
                }
            } else if (initCord[0] + 1 == Game.DIM_X) {
                for (int i = initCord[0]-1; i <= initCord[0]; i++) {
                  for (int j = initCord[1] - 1; j <= initCord[1]; j++) {
                      if(x == i && y == j){
                          other.receiveMissileAttack(damage);
                      }
                  }
                }

             }
            return true;
        }
        return false;
    }

    @Override
    public void onDelete() {
        if(shield==0) {
            kamikaze = true;
            game.receivePoints(pointValue);
            reduceForces();
        }
    }

    @Override
    public String toString() {
        return "*<" + shield + ">*";
    }
}
