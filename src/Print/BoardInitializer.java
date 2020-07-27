package Print;

import Game.Game;
import GameElements.Ships.Enemies.CarrierShip;
import GameElements.Ships.Enemies.Destroyer;
import GameElements.Ships.Enemies.UFO;
import Game.Level;

public class BoardInitializer {
    private Level level ;
    private Board board;
    private Game game;

    public Board initialize (Game game, Level level) {
        this.level = level;
        this.game = game;
        board = new Board(Game.DIM_X, Game.DIM_Y);
        initializeUfo() ;
        initializeCarrierShips () ;
        initializeDestroyers () ;
        return board;
    }
    private void initializeUfo () {
        // TODO implement
        board.add(new UFO(game, Game.DIM_Y , 0));
    }
    private void initializeCarrierShips () {
        // TODO implement
        int baseXCoord = 2;
        int baseYCoord = 1;

        if (level.equals(Level.HARD) || level.equals(Level.INSANE)){
            for (int i = 0; i < level.getNumCarrierShips(); i++) {
                if(baseXCoord == 6){
                    baseXCoord = 2;
                    baseYCoord++;
                }
                board.add(new CarrierShip(game,baseXCoord++,baseYCoord));
            }
        } else{
            for (int i = 0; i < level.getNumCarrierShips(); i++) {
                    board.add(new CarrierShip(game,baseXCoord++,baseYCoord));
            }
        }
    }
    private void initializeDestroyers () {
            // TODO implement
        int baseXCoord = 3;
        int baseYCoord = 2;

        if(level.equals(Level.HARD)){
            baseXCoord = 2;
            baseYCoord = 3;
            for (int i = 0; i < level.getNumDestroyers(); i++) {
                board.add(new Destroyer(game,baseXCoord++,baseYCoord));
            }

        } else if(level.equals(Level.INSANE)){
            baseXCoord = 2;
            baseYCoord = 4;
            for (int i = 0; i < level.getNumDestroyers(); i++) {
                board.add(new Destroyer(game,baseXCoord++,baseYCoord));
            }
        } else {
            for (int j = 0; j < level.getNumDestroyers(); j++) {
                board.add(new Destroyer(game, baseXCoord++, baseYCoord));
            }

        }
    }
}