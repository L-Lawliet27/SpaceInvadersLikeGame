package Utils;

import Game.Game;
import Game.Level;

public class FileContentsVerifier{
    public static final String separator1 = ";";
    public static final String separator2 = ",";
    public static final String labelRefSeparator = " - ";
    private String foundInFileString = "";
    private String[] prefixes = new String[8];

    private void appendToFoundInFileString(String linePrefix) {
        foundInFileString += linePrefix;
    }


//    VERIFY CYCLE {
    // Don’t catch NumberFormatException.
    public boolean verifyCycleString(String cycleString) {
        String[] words = cycleString.split (separator1);
        appendToFoundInFileString(words[0]);
        if (words.length != 2
                || ! verifyCurrentCycle(Integer.parseInt(words[1])))
            return false;
        return true;
    }
    //  }



    //    VERIFY LEVEL {
    public boolean verifyLevelString(String levelString) {
        String[] words = levelString.split(separator1);
        appendToFoundInFileString(words[0]);
        if (words.length != 2
                || ! verifyLevel (Level. parse(words[1])))
            return false;
        return true;
    }
    //  }


    //    VERIFY UFO {
    // Don’t catch NumberFormatException.
    public boolean verifyUfoString(String lineFromFile, Game game, int armour) {
        String[] words = lineFromFile.split(separator1);
        appendToFoundInFileString(words[0]);
        if (words.length != 3) return false;
        String[] coords = words[1].split (separator2);
        if ( ! verifyCoords(Integer. parseInt(coords[0]) , Integer. parseInt(coords[1]) , game)
                || ! verifyShield(Integer. parseInt(words[2]), armour) )
            return false;
        return true;
    }
    //  }


    //    VERIFY PLAYER {
    // Don’t catch NumberFormatException.
    public boolean verifyPlayerString(String lineFromFile, Game game, int armour) {
        String[] words = lineFromFile.split(separator1);
        appendToFoundInFileString(words[0]);
        if (words.length != 6 && !words[0].equals("P")) return false;
        String[] coords = words[1].split (separator2);
        if ( ! verifyCoords(Integer. parseInt(coords[0]) , Integer. parseInt(coords[1]) , game)
                || ! verifyShield(Integer.parseInt(words[2]), armour)
                || ! verifyPoints (Integer. parseInt(words[3]))
                || ! verifyBool(words[4]) )
            return true;
        return false;
    }
    //  }


    //    VERIFY ALIENSHIPS {
    // Don’t catch NumberFormatException.
    public boolean verifyAlienShipString(String lineFromFile, Game game, int armour) {
        String[] words = lineFromFile.split(separator1);
        appendToFoundInFileString(words[0]);
        if (words.length != 5 && words.length != 6 && !words[0].equals("C")
        && !words[0].equals("D") && !words[0].equals("E")) return false;
        String[] coords = words[1].split (separator2);
        if(words[0].equals("D")){
            String[] splitOnRef = lineFromFile.split(labelRefSeparator);
            if (!verifyCoords(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), game)
                    || !verifyShield(Integer.parseInt(words[2]), armour)
                    || !verifyCycleToNextAlienMove(Integer.parseInt(words[3]), game.getLevel())
                    || !verifyDir(words[4])
                    || !verifyLabel(splitOnRef[1])) {

                return false;
            }
        }
        else if ( ! verifyCoords(Integer. parseInt(coords[0]) , Integer. parseInt(coords[1]) , game)
                || ! verifyShield(Integer.parseInt(words[2]), armour)
                || ! verifyCycleToNextAlienMove(Integer.parseInt(words[3]), game.getLevel())
// supposes that Direction is an enum with a parse method (similar to that of the Level enum)
                || ! verifyDir (words[4])) {
            return false;
        }
        return true;
    }
    //  }


    //    VERIFY WEAPON {
    // Don’t catch NumberFormatException.
    public boolean verifyWeaponString(String lineFromFile, Game game) {
        String[] words = lineFromFile.split(separator1);
        if (words.length != 2) return false;
        appendToFoundInFileString(words[0]);


        if(words[0].equals("M")){
            String[] coords = words[1].split (separator2);
            if ( ! verifyCoords(Integer. parseInt(coords[0]) , Integer. parseInt(coords[1]) , game) )
                return false;
        }
        else {
            String[] origin = words[1].split(labelRefSeparator);
            String[] coords = origin[0].split(separator2);
            if (!verifyCoords(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]), game) && !verifyRefString(words[1]) ) {
                return false;
            }
        }
        return true;
    }
    //  }



    public boolean verifyRefString(String lineFromFile) {
        String[] words = lineFromFile.split(labelRefSeparator);
        if (words.length != 2 || ! verifyLabel(words[1])) return false;
        return true;
    }
    public static boolean verifyLabel(String label) {
        return Integer.parseInt(label) > 0;
    }
    public static boolean verifyCoords(int x, int y, Game game) {
        return game.isOnBoard(x, y);
    }
    public static boolean verifyCurrentCycle(int currentCycle) {
        return currentCycle >= 0;
    }
    public static boolean verifyLevel(Level level) {
        return level != null;
    }
    // supposes that Direction is an enum

    //In my case, the direction is a string
    public static boolean verifyDir(String dir) {
        return dir != null;
    }

    public static boolean verifyShield(int shield, int initialShield ) {
        return 0 < shield && shield <= initialShield;
    }
    public static boolean verifyPoints(int points) {
        return points >= 0;
    }
    public static boolean verifyCycleToNextAlienMove(int cycle, Level level) {
        return 0 <= cycle && cycle <= level.getNumCyclesToMoveOneCell();
    }
    // parseBoolean converts any string different from "true" to false.
    public static boolean verifyBool(String boolString) {
                if(boolString.equals("true") || boolString.equals("hasShock") || boolString.equals("hasSuperMissile")
                        || boolString.equals("left")){
                    return true;
                } else if( boolString.equals("false") || boolString.equals("doesntHaveShock") || boolString.equals("doesntHaveSuperMissile")
                        || boolString.equals("right")) {
                    return false;
                } else
                return false;
    }
    public static boolean verifyMissiles(boolean boolString) {
             return boolString;
    }
    public boolean isMissileOnLoadedBoard() {
        return foundInFileString.toUpperCase().contains("M") || foundInFileString.toUpperCase().contains("X");
    }
    // Use a regular expression to verify the string of concatenated prefixes found

    public boolean verifyLines() {
    // TODO: compare foundInFileString with a regular expression
        return foundInFileString.toUpperCase().contains("U") && foundInFileString.toUpperCase().contains("D")
                && foundInFileString.toUpperCase().contains("B") && foundInFileString.toUpperCase().contains("P")
                || foundInFileString.toUpperCase().contains("E");
    }
    // text explaining allowed concatenated prefixes
    public String toString() {
    // TODO
        prefixes[0]= "Ufo: U;x,y;shield\n";
        prefixes[1]= "Carrier ship: C;x,y;shield;cyclesNextAlienMove;dir\n";
        prefixes[2]= "Destroyer: D;x,y;shield;cyclesNextAlienMove;dir\n";
        prefixes[3]= "Explosive ship: E;x,y;shield;cyclesNextAlienMove;dir\n";
        prefixes[4]= "Bomb: B;x,y\n";
        prefixes[5]= "Missile: M;x,y\n";
        prefixes[6]= "SuperMissile: X;x,y\n";
        prefixes[7]= "UCMShip (player): P;x,y;shield;points;hasShock;superMissiles\n";

        return prefixes[0]+prefixes[1]+prefixes[2]+prefixes[3]+prefixes[4]+prefixes[5]+prefixes[6]+prefixes[7];
    }
}