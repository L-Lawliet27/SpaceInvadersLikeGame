package Utils;

import Game.Game;

public interface IExecuteRandomActions {

    static boolean canGenerateUfo(Game game){
        return game.getRandom().nextInt(100) < 100 * game.getLevel().getUfoFrequency();
    }

    static boolean canTurnExplosive(Game game){
       return game.getRandom().nextInt(100) < 100 * game.getLevel().turnExplosiveFrequency();
    }

    static boolean canGenerateBomb(Game game){
        return game.getRandom().nextInt(100) < 100 * game.getLevel().getShootFrequency();
    }
}