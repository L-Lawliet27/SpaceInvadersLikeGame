package GameElements.Ships.Enemies;

import Game.Game;
import GameElements.GameElement;
import GameElements.Ships.UCMShip;
import Utils.FileContentsVerifier;
import Game.Level;

public class AlienShip extends EnemyShip {

    private static int numForces = 0;
    private static boolean landed = false;
    private String[] splitOnRef;
    protected static boolean rightToLeft = true;
    protected static boolean down = false;
    protected static int counter;
    protected static boolean isFirst = true;
    protected static int elemCounter = 0;
    protected static boolean goToEasy = false;
    protected static boolean goToHard = false;

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

            firstShipCheck();
            downCheck();
            setGoToEasy();
            setGoToHard();

            if (getDir()) {
                initCord[0]--;

                if (!game.isOnBoard(initCord[0], initCord[1])) {
                    initCord[0]++;
                    initCord[1]++;
                    initCord[0]++;
                    rightToLeft = false;
                    down = true;
                }

                //                } else if(!game.isOnBoard(initCord[0] - 1, initCord[1] - 1)){
//                    initCord[0]++;
//                    initCord[1]++;
//                    initCord[0]++;
//                    rightToLeft = false;
//                    down = true;
//                }

            } else {
                initCord[0]++;
                if (!game.isOnBoard(initCord[0], initCord[1])) {
                    initCord[0]--;
                    initCord[1]++;
                    initCord[0]--;
//                    rightToLeft = true;
//                    down = true;
                } else if(game.getLevel() == Level.EASY || goToEasy){

                    if(!game.isOnBoard(initCord[0] + (getNumForces() - 2), initCord[1]) && isFirst){
                        downSet();
                    }
                } else if (game.getLevel() == Level.HARD || goToHard){

                    if(getNumForces() <= 8 && getNumForces() > 6){
                        if(!game.isOnBoard(initCord[0] + (getNumForces() - 4), initCord[1]) && isFirst){
                            downSet();
                        }
                    } else if(!game.isOnBoard(initCord[0] + (getNumForces() - 8), initCord[1]) && isFirst){
                        downSet();
                    }
                } else if (game.getLevel() == Level.INSANE){

                    if(!game.isOnBoard(initCord[0] + (getNumForces() - 12), initCord[1]) && isFirst){
                        downSet();
                    }
                }

            }
            elemCounter++;

            setIsFirst();
            setLanded();
        }
    }


    private void firstShipCheck(){
        if(elemCounter == getNumForces()){
            elemCounter = 0;
            isFirst = true;
        }
    }

    private void setIsFirst(){
        if(isFirst){
            isFirst = false;
        }
    }

    private void downCheck(){
        if(down){
            initCord[1]++;
            counter++;
            if(counter == getNumForces()){
                down = false;
                counter = 0;
            }
        }
    }

    private void setLanded(){
        if (initCord[1] == Game.DIM_Y - 1) {
            landed = true;
            game.getWinnerMessage();
        }
    }

    private void setGoToEasy(){
        if(game.getLevel() == Level.HARD || game.getLevel() == Level.INSANE){
            if(getNumForces() <= 6){
                goToEasy = true;
            }
        }
    }

    private void setGoToHard(){
        if(game.getLevel() == Level.INSANE){
            if(getNumForces() <= 12 && getNumForces() > 6){
                goToHard = true;
            }
        }
    }

    private void downSet(){
        initCord[0]--;
        initCord[1]++;
        initCord[0]--;
        rightToLeft = true;
        down = true;
    }


}
