package GameElements.Weapons;

import Game.Game;
import GameElements.GameElement;
import GameElements.Ships.Enemies.Destroyer;
import Utils.FileContentsVerifier;

public class Bomb extends Weapon{

    private Destroyer origin;
    private int ownerRef;

    public Bomb(Game game, int dimX, int dimY, Destroyer origin) {
        super(game, dimX, dimY, 1, 1);
        this.origin = origin;
        ownerRef = origin.label();
    }

    public Bomb(Game game, int dimX, int dimY, Destroyer origin, int ownerRef) {
        super(game, dimX, dimY, 1, 1);
        this.origin = origin;
        this.ownerRef = ownerRef;
    }

    public Bomb() {

    }

    @Override
    public void computerAction() {

    }

    @Override
    public void onDelete() {
        origin.setDropBomb();
    }

    @Override
    public void move() {
        initCord[1]++;

        if(!game.isOnBoard(initCord[0],initCord[1])){
            setInvisible();
            origin.setDropBomb();
        }
    }

    @Override
    public boolean performAttack(GameElement other) {
        if(initCord[0]==other.getCord('x') && initCord[1]==other.getCord('y')){
            if(other.receiveBombAttack(damage)){
                doneDamage(damage);
                setInvisible();
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean receiveMissileAttack(int damage) {
        doneDamage(damage);
        setInvisible();
        return true;
    }

    @Override
    public String stringify() {
        serial[0] = "B;";
        serial[1] = initCord[0] + "," + initCord[1] + "";

        return serial[0] + serial[1] + (origin.isAlive() ? generateSerialRef() : "");
    }


    @Override
    public String toString() {
        return "!";
    }


    public String generateSerialRef() {
        return labelRefSeparator + ownerRef;
    }
}
