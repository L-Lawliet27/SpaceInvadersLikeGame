package GameElements;

import Utils.FileContentsVerifier;
import Utils.IAttack;
import Game.Game;

public abstract class GameElement implements IAttack {
    protected int shield;
    protected Game game;
    protected int[] initCord = new int[2];
    protected String[] serial;
    public static final String labelRefSeparator = " - ";
    protected int label = 1;
    protected String[] line;
    protected String[] coord;

    public GameElement(Game game, int dimX, int dimY, int lives){
        this.game = game;
        initCord[0] = dimX;
        initCord[1] = dimY;
        shield = lives;

    }

    public GameElement() {

    }


    public boolean isAlive(){
        return shield > 0;
    }

    public int getShield(){
        return shield;
    }

    public boolean isOnPosition(int x, int y)
    {
        return initCord[0] == x && initCord[1] == y;
    }

    public int getCord(char z){
       if(z == 'x') return initCord[0];
       else if(z == 'y') return initCord[1];
       return -1;
    }

    public void doneDamage(int damage)
    {
        shield = ( damage >= shield ? 0 : shield - damage);
    }

    public boolean isOut(){
        return !game.isOnBoard(initCord[0], initCord[1]);
    }

    public abstract void computerAction();
    public abstract void onDelete();
    public abstract void move();
    public abstract String toString();


    @Override
    public boolean performAttack(GameElement other) {
        return false;
    }


    @Override
    public boolean receiveMissileAttack(int damage) {
        return false;
    }


    @Override
    public boolean receiveBombAttack(int damage) {
        return false;
    }


    @Override
    public boolean receiveShockWaveAttack(int damage) {
        return false;
    }


    public abstract String stringify();

    public abstract GameElement parse(String inLine, Game game, FileContentsVerifier verifier);

    public int getLabel() {
        return label;
    }

    public int label(){
        int prevLabel = label;
        label++;
        return prevLabel;
    }

    public abstract boolean isOwner(int ref);

}
