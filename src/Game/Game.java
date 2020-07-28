package Game;

import Exceptions.FileContentsException;
import Exceptions.MissileInFlightException;
import Exceptions.NoShockwaveException;
import Exceptions.OffWorldException;
import GameElements.GameElement;
import GameElements.GameElementGenerator;
import GameElements.Ships.Enemies.AlienShip;
import GameElements.Ships.Enemies.Destroyer;
import GameElements.Ships.UCMShip;
import GameElements.Weapons.Missile;
import GameElements.Weapons.ShockWave;
import GameElements.Weapons.SuperMissile;
import Print.Board;
import Print.BoardInitializer;
import Utils.FileContentsVerifier;
import Utils.IPlayerController;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Random;

public class Game implements IPlayerController{

    public final static int DIM_X = 8;
    public final static int DIM_Y = 9;

    private Board board;
    private UCMShip player;
    private Random rand;
    private int currentCycle;
    private Level level;
    private BoardInitializer initializer;
    private boolean doExit;
    private String[] info;
    private boolean stringifying = false;
    String[] serial;
    private boolean loading = true;
    private FileContentsVerifier verifier;

    public Game (Level gameLevel, Random random){
        this.rand = random;
        this.level = gameLevel;
        initializer = new BoardInitializer();
        info = new String[6];
        serial = new String[3];
        initGame();
    }
    public void initGame () {
        currentCycle = 0;
        board = initializer.initialize(this, level );
        player = new UCMShip(this, DIM_X / 2, DIM_Y -2);
        board.add(player);
    }

    public Random getRandom() {
        return rand;
    }

    public Level getLevel(){
        return level;
    }

    public void reset(){
        AlienShip.onReset();
        initGame();
    }

    public void addObject(GameElement object) {
        board.add(object);
    }

    public String positionToString(int x, int y) {
        return board.toString(x, y);
    }

    public boolean isFinished() {
        return playerWin() || aliensWin() || doExit;
    }


    public boolean aliensWin() {

        return !player.isAlive () || AlienShip.haveLanded();
    }
    private boolean playerWin() {
        return AlienShip.allDead();
    }

    public void update() {
        board.computerAction();
        board.update();
        currentCycle += 1;
    }

    public boolean isOnBoard(int x, int y) {

        return x >= 0 && x <= DIM_X && y >= 0 && y < DIM_Y /* range condition on the coordinates */;
    }


    public void exit() {
        doExit = true;
    }

    public String infoToString() {

        info[0] = "Score: " + currentPoints() + "\n";
        info[1] = "Shield Strength: " + player.getShield() + "\n";

        if(player.getEnShock())
            info[2] ="Shockwave: yes" + "\n";
        else info[2] = "Shockwave: no" + "\n";

        if(player.getSM())
            info[3] = "SuperMissile: yes" + "\n";
        else info[3] = "SuperMissile: no" + "\n";

        info[4] = "Cycle Number: " + getCurrentCycle() + "\n";
        info[5] = "Remaining Alien Ships: " + AlienShip.getNumForces() + "\n";

        /* game−state string to be printed with the board */

        return info[0] + info[1] + info[2] + info[3] + info[4] + info[5];
    }


    public String getWinnerMessage() {
        if (playerWin()) return "Player win!";
        else if (aliensWin()) return "Aliens win!";
        else if (doExit) return "Player exits the game";
        else return "This should not happen";
    }


    @Override
    public boolean move(int numCells) throws OffWorldException {
        if(isOnBoard(player.getCord('x')+numCells, player.getCord('y'))) {
            player.move(numCells);
            update();
        } else throw new OffWorldException("Failed to move\n" +
                "Cause of Exception:\n" +
                "pr2.exceptions.OffWorldException: Cannot perform move: ship too near border");
        return false;
    }

    @Override
    public boolean shootMissile() throws MissileInFlightException {
        if(!player.getEnMiss() && player.canShoot()){
            enableMissile();
            setCanShoot();
            addObject(new Missile(this, player.getCord('x'), player.getCord('y')));
            update();
        } else throw new MissileInFlightException("Failed to shoot\n" +
                "Cause of Exception:\n" +
                "pr2.exceptions.MissileInFlightException: Cannot fire missile: missile already exists on board");
        return true;
    }

    public boolean shootSuperMissile() throws MissileInFlightException {

        if(!player.getEnMiss() && player.canShoot() && statSuperMissile()){
             enableMissile();
            setCanShoot();
            setSuperMissile();
            addObject(new SuperMissile(this, player.getCord('x'), player.getCord('y')));
            update();
        }else throw new MissileInFlightException("Failed to shoot\n" +
                "Cause of Exception:\n" +
                "pr2.exceptions.MissileInFlightException: Cannot fire missile: missile already exists on board");
        return true;

    }

    @Override
    public boolean shockWave() throws NoShockwaveException {

        if(player.getEnShock()){
            enableShockWave();
            addObject(new ShockWave(this, player.getCord('x'), player.getCord('y')));
            update();
        } else throw new NoShockwaveException("Failed to shoot\n" +
                "Cause of Exception:\n" +
                "pr2.exceptions.NoShockwaveException: Cannot release shockwave: no shockwave available" + "%n%n");

        return true;
    }

    @Override
    public void receivePoints(int points) {
        player.setPoints(points);
    }

    @Override
    public void enableShockWave() {
        player.changeShockwaveStat();
    }

    @Override
    public void enableMissile() {
            player.changeMissileStat();
    }

    public void setCanShoot(){
        player.setTakeShot();
    }

    public int currentPoints(){
        return player.getPoints();
    }


    public void setSuperMissile(){
        player.changeSuperMissileStat();
    }

    public boolean statSuperMissile(){
        return player.getSM();
    }

    public void replaceElement(GameElement e1, GameElement e2){
        board.replaceElem(e1,e2);
    }

    public String stringify() {
        stringifying = false;

        serial[0] = "- - - Space Invaders <2.0> - - -" + "\n\n";
        serial[1] = "G;" + getCurrentCycle() + "\n";
        serial[2] = "L;" + level.toString() + "\n";

        return serial[0] + serial[1] + serial[2] + board.stringify();

    }

    public void setStringifying(){
        stringifying = true;
    }

    public boolean isStringifying(){
        return stringifying;
    }


    public int numCyclesToMoveCell(){
        return level.getNumCyclesToMoveOneCell();
    }

    public int getCurrentCycle(){
        return currentCycle;
    }

    public int movingCycles(){
        return getCurrentCycle() % numCyclesToMoveCell();
    }


    public void load(BufferedReader inStream) throws FileContentsException, IOException {
        loading = false;
        verifier = new FileContentsVerifier();
        boolean isP= false;
        String line = inStream.readLine().trim();
        Board ldBoard = new Board(Game.DIM_X,Game.DIM_Y);

        if(verifier.verifyCycleString(line)){
            String[] cycle = line.split(";");
            currentCycle = Integer.parseInt(cycle[1]);
        } else throw new FileContentsException("File Error: Foreign Cycle");

        line = inStream.readLine().trim();

        if(verifier.verifyLevelString(line)){
            String[] lvl = line.split(";");
            level = Level.parse(lvl[1]);
        } else throw new FileContentsException("File Error: Foreign Level");

        line = inStream.readLine().trim();

        while( line != null && !line.isEmpty() ) {
            GameElement gameElement = GameElementGenerator.parse(line, this, verifier);
            String[] type = line.split(";");


            if(type[0].equals("P")){
                player = (UCMShip) gameElement;
                ldBoard.add(player);
                isP = true;
            } else if (gameElement == null)
                throw new FileContentsException("invalid file, unknown line prefix");

            if(!isP) {
                ldBoard.add(gameElement);
            }
            line = inStream.readLine().trim();
        }

        inStream.close();

        if (verifier.isMissileOnLoadedBoard()){
            if(FileContentsVerifier.verifyMissiles(player.getEnMiss())){
                player.changeMissileStat();
            }

            if(FileContentsVerifier.verifyMissiles(player.canShoot())){
                player.setTakeShot();
            }
        }

        board = ldBoard;

    }


    public Destroyer getBombOwner(int ref) {
        return (Destroyer) board.getLabelOwner(ref); // ugly cast
    }
}