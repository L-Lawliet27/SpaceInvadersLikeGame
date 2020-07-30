package GameElements;

import Exceptions.FileContentsException;
import Game.Game;
import GameElements.Ships.UCMShip;
import GameElements.Ships.Enemies.*;
import GameElements.Weapons.*;
import Utils.FileContentsVerifier;

public class GameElementGenerator {

    private static GameElement[] availableGameElements = new GameElement[]{
            new UCMShip(),
            new UFO(),
            new CarrierShip(),
            new Destroyer(),
            new ExplosiveShip(),
            new ShockWave(),
            new Bomb(),
            new Missile(),
            new SuperMissile()
    };
    public static GameElement parse(String stringFromFile, Game game, FileContentsVerifier verifier) {
        GameElement gameElement = null;
        for (GameElement ge: availableGameElements) {
            gameElement = ge.parse(stringFromFile, game, verifier);
            if (gameElement != null) break;
        }
        return gameElement;
    }
}
