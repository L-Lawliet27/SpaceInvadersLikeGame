package GameElements.Weapons;

import Game.Game;
import GameElements.GameElement;

public class Missile extends Weapon{


    public Missile(Game game, int dimX, int dimY) {
        super(game, dimX, dimY, 1, 1);
    }

    protected Missile(Game game, int dimX, int dimY, int damage) {
        super(game, dimX, dimY, 1, damage);
    }

    public Missile() {}


    @Override
    public void computerAction() {

    }

    @Override
    public void onDelete() {
        game.enableMissile();
        game.setCanShoot();
    }

    @Override
    public void move() {
        initCord[1]--;

//        if(!isVisible()){
//            setVisible();
//        }

        if(!game.isOnBoard(initCord[0],initCord[1])){
            doneDamage(damage);
            setInvisible();
        }
    }

    @Override
    public String toString() {
//        if(damage == 2){
//            return "/s\\";
//        }
        return "^";
    }

    @Override
    public boolean performAttack(GameElement other) {
        if(initCord[0]==other.getCord('x') && initCord[1]==other.getCord('y')){
            if(other.receiveMissileAttack(damage)){
                doneDamage(damage);
                setInvisible();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean receiveBombAttack(int damage) {
        doneDamage(damage);
        setInvisible();
        return true;
    }

    @Override
    public String stringify() {
        serial[0] = "M;";
        serial[1] = initCord[0] + "," + initCord[1] + "";

        return serial[0] + serial[1];
    }


}
