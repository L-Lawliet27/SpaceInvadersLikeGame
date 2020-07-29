package GameElements.Ships;

import Exceptions.MissileInFlightException;
import Exceptions.NoShockwaveException;
import Exceptions.OffWorldException;
import Game.Game;
import GameElements.GameElement;
import Utils.FileContentsVerifier;

public class UCMShip extends Ship{

    private boolean enShock, enMiss, takeShot, sMissile;
    private int points;
    private int mov;

    public UCMShip(Game game, int dimX, int dimY) {
        super(game, dimX, dimY, 3);
        enShock = false;
        enMiss = false;
        takeShot = true;
        sMissile = false;
        points = 0;
        mov = 0;
        serial = new String[6];
    }

    public UCMShip() {
        super();
    }

    public UCMShip(Game game, int dimX, int dimY, int shield, int points, boolean enShock, boolean sMissile) {
        super(game, dimX, dimY, shield);
        this.enShock = enShock;
        enMiss = false;
        takeShot = true;
        this.sMissile = sMissile;
        this.points = points;
        mov = 0;
        serial = new String[6];
    }



    @Override
    public String stringify() {

        serial[0] = "P;";
        serial[1] = initCord[0] + "," + initCord[1] + ";";
        serial[2] = getShield() + ";";
        serial[3] = getPoints() + ";";
        if(getEnShock()) serial[4] = "hasShock;";
        else serial[4] = "doesntHaveShock;";

        if(getSM()) serial[5] = "hasSuperMissile";
        else serial[5] = "doesntHaveSuperMissile";

        return serial[0]+serial[1]+serial[2]+serial[3]+serial[4]+serial[5];
    }

    @Override
    public GameElement parse(String inLine, Game game, FileContentsVerifier verifier) {
        line = inLine.split(";");
        coord = line[1].split(",");

        if(verifier.verifyPlayerString(inLine, game, Integer.parseInt(line[2]))){
            return new UCMShip(game, Integer.parseInt(coord[0]), Integer.parseInt(coord[1]),
                    Integer.parseInt(line[2]),Integer.parseInt(line[3]),
                    FileContentsVerifier.verifyBool(line[4]),
                    FileContentsVerifier.verifyBool(line[5]));
        }
        return null;
    }


    public void setPoints(int p){
          points += p;
    }

    public int getPoints() {
        return points;
    }

    public void setTakeShot(){
        takeShot = !takeShot;
    }

    public boolean canShoot(){
        return takeShot;
    }

    public void changeShockwaveStat(){ enShock = !enShock;}

    public void changeMissileStat(){ enMiss = !enMiss;}

    public void changeSuperMissileStat(){ sMissile = !sMissile;}

    public boolean getEnShock(){
        return enShock;
    }

    public boolean shock() throws NoShockwaveException {
        if(!getEnShock()) throw new NoShockwaveException("Cannot release shockwave: no shockwave available");
        else return true;
    }


    public boolean missile() throws MissileInFlightException {
        if(getEnMiss() && !canShoot()) throw new MissileInFlightException("Failed to shoot\n" +
                "Cause of Exception:\n" +
                "pr2.exceptions.MissileInFlightException: Cannot fire missile: missile already exists on board");
        else return true;
    }

    public boolean superMissile() throws MissileInFlightException {
        if(getEnMiss() && !canShoot() && getSM())
            throw new MissileInFlightException("Failed to shoot\n" +
                "Cause of Exception:\n" +
                "pr2.exceptions.MissileInFlightException: Cannot fire missile: missile already exists on board");
        else return true;
    }

    public boolean getEnMiss(){
        return enMiss;
    }

    public boolean getSM(){ return sMissile;}

    public void move(int numCells) {
        mov = numCells;
        move();
    }

    public boolean onBoard(int numCells) throws OffWorldException {

        if(!game.isOnBoard(getCord('x') + numCells, getCord('y'))){
            throw new OffWorldException("Failed to move\n" +
                    "Cause of Exception:\n" +
                    "pr2.exceptions.OffWorldException: Cannot perform move: ship too near border");
        } else return true;


    }

    @Override
    public void computerAction() { }

    @Override
    public void onDelete() { }

    @Override
    public void move() {
        if(isAlive()) {
            initCord[0] += mov;
        }
    }

    @Override
    public boolean receiveBombAttack(int damage) {
        doneDamage(damage);
        return true;
    }

    @Override
    public String toString() {

        if(isAlive()) {
            return "/-^-\\";
        }
        return "_+.+_";
    }





}
