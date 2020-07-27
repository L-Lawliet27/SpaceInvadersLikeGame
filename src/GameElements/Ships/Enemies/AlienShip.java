package GameElements.Ships.Enemies;

import Game.Game;
import GameElements.GameElement;
import GameElements.Ships.UCMShip;
import Utils.FileContentsVerifier;

public class AlienShip extends EnemyShip {

    private static int numForces = 0;
    private static boolean landed = false;
    private String[] splitOnRef;
    protected static boolean rightToLeft = true;
    protected static boolean down = false;
    protected static int counter;

    public AlienShip(Game game, int dimX, int dimY, int lives, int pointValue) {
        super(game, dimX, dimY, lives, pointValue);
        numForces++;
        serial = new String[5];
        counter = 1;
    }

    public AlienShip() {
    }



    public static int getNumForces() {
        return numForces;
    }

    public static void reduceForces(){ numForces--;}

    public static boolean allDead(){
        return getNumForces() == 0;
    }

    public static boolean haveLanded(){
        return landed;
    }

    public boolean getDir(){ return rightToLeft;}


    @Override
    public GameElement parse(String inLine, Game game, FileContentsVerifier verifier) {
        splitOnRef = inLine.split(labelRefSeparator);
        line = splitOnRef[0].split(";");
        coord = line[1].split(",");

        if(verifier.verifyAlienShipString(inLine, game, Integer.parseInt(line[2]))){
            switch (line[0].toUpperCase()){
                case "C": return new CarrierShip(game, Integer.parseInt(coord[0]), Integer.parseInt(coord[1]),
                        Integer.parseInt(line[2]), FileContentsVerifier.verifyBool(line[3]));
                case "D": return new Destroyer(game, Integer.parseInt(coord[0]), Integer.parseInt(coord[1]),
                        Integer.parseInt(line[2]), FileContentsVerifier.verifyBool(line[3]));
                case "E": return new ExplosiveShip(game, Integer.parseInt(coord[0]), Integer.parseInt(coord[1]),
                        Integer.parseInt(line[2]), FileContentsVerifier.verifyBool(line[3]));
            }
        }
        return null;
    }

    @Override
    public void onDelete() {
        if(shield==0) game.receivePoints(pointValue);
        reduceForces();
    }

    public static void onReset(){
        numForces = 0;
    }

    @Override
    public void move() {
        if(game.movingCycles() == 0) {

            if(down){
                initCord[1]++;
                counter++;
                if(counter == getNumForces()){
                    down = false;
                    counter = 0;
                }
            }

            if (getDir()) {
                initCord[0]--;
                if (!game.isOnBoard(initCord[0], initCord[1])) {
                    initCord[0]++;
                    initCord[1]++;
                    initCord[0]++;
                    rightToLeft = false;
                    down = true;
                }

            } else {
                initCord[0]++;
                if (!game.isOnBoard(initCord[0], initCord[1])) {
                    initCord[0]--;
                    initCord[1]++;
                    initCord[0]--;
                    rightToLeft = true;
                    down = true;
                }

            }


            if (initCord[1] == Game.DIM_Y - 1) {
                landed = true;
                game.getWinnerMessage();
            }
        }
    }


}
