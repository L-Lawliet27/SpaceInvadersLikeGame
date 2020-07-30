package GameElements.Weapons;

import Game.Game;
import GameElements.GameElement;

public class ShockWave extends Weapon{
    public ShockWave(Game game, int dimX, int dimY) {
        super(game, dimX, dimY, 1, 1);
    }

    public ShockWave() {
    }

    @Override
    public void computerAction() {

    }

    @Override
    public void onDelete() {
        game.enableShockWave();
    }

    @Override
    public void move() {

    }

    @Override
    public String toString() {
        return null;
    }


    @Override
    public boolean performAttack(GameElement other) {
        other.receiveShockWaveAttack(damage);
        doneDamage(damage);
//        setInvisible();
        return true;
    }

    @Override
    public String stringify() {
        return null;
    }

    @Override
    public boolean isOwner(int ref) {
        return false;
    }
}
