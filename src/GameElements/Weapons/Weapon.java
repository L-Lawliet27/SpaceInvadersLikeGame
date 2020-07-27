package GameElements.Weapons;

import Game.Game;
import GameElements.GameElement;
import GameElements.Ships.Enemies.CarrierShip;
import GameElements.Ships.Enemies.Destroyer;
import GameElements.Ships.Enemies.ExplosiveShip;
import Utils.FileContentsVerifier;

public abstract class Weapon extends GameElement {
    protected int damage;
    protected boolean visible;
    private String[] splitOnRef;


    public Weapon(Game game, int dimX, int dimY, int lives, int damage) {
        super(game, dimX, dimY, lives);
        this.damage = damage;
        visible = true;
        serial = new String[2];
    }

    public Weapon() {
    }

    @Override
    public GameElement parse(String inLine, Game game, FileContentsVerifier verifier) {
        splitOnRef = inLine.split(labelRefSeparator);
        line = splitOnRef[0].split(";");
        coord = line[1].split(",");

        if(verifier.verifyWeaponString(inLine, game)){
            switch (line[0].toUpperCase()){
                case "M": return new Missile(game, Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
                case "X": return new SuperMissile(game, Integer.parseInt(coord[0]), Integer.parseInt(coord[1]));
                case "B": return new Bomb(game, Integer.parseInt(coord[0]), Integer.parseInt(coord[1]),
                        game.getBombOwner(Integer.parseInt(splitOnRef[1])), Integer.parseInt(splitOnRef[1]));
            }
        }
        return null;
    }
    public boolean isVisible(){
        return visible;
    }

    void setInvisible(){
        visible = false;
    }

    void setVisible(){
        visible = true;
    }
}
  //  Java.text.messagetext     string.format("{0} {1}",1,2)