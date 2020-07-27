package Utils;

import Exceptions.MissileInFlightException;
import Exceptions.NoShockwaveException;
import Exceptions.OffWorldException;

public interface IPlayerController {
    // Player actions
    boolean move(int numCells) throws OffWorldException;
    boolean shootMissile() throws MissileInFlightException;
    boolean shockWave() throws NoShockwaveException;
    // Callbacks
    void receivePoints(int points);
    void enableShockWave();
    void enableMissile();
}
