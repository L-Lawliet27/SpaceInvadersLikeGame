package GameElements.Ships.Enemies;

import Game.Game;
import GameElements.GameElement;
import GameElements.Ships.Ship;
import Utils.FileContentsVerifier;

public class EnemyShip extends Ship {

    protected int pointValue;

    public EnemyShip(Game game, int dimX, int dimY, int lives, int pointValue) {
        super(game, dimX, dimY, lives);
        this.pointValue = pointValue;
    }

    public EnemyShip() {

    }

    @Override
    public void computerAction() {

    }

    @Override
    public void onDelete() {
        if(shield==0) game.receivePoints(pointValue);
    }

    @Override
    public void move() {

    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public boolean receiveMissileAttack(int damage) {
        doneDamage(damage);
        return true;
    }

    @Override
    public boolean receiveShockWaveAttack(int damage) {
        doneDamage(damage);
        return true;
    }

    @Override
    public String stringify() {
        return null;
    }

    @Override
    public GameElement parse(String inLine, Game game, FileContentsVerifier verifier) {
        return null;
    }

}
