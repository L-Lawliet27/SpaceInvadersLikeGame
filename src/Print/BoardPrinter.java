package Print;

import Game.Game;
import Utils.*;

public class BoardPrinter extends GamePrinter {

    private int numRows;
    private int numCols;
    private String[][] board;
    final String space = " ";


    public BoardPrinter(int rows, int cols) {
        numRows = rows;
        numCols = cols;
    }


    private void encodeGame(Game game) {
        board = new String[numRows][numCols];
        for(int i = 0; i < numRows; i++) {
            for(int j = 0; j < numCols; j++) {
                // your code here
                //Logic of the game->Here the elements according to the coordinates of i and j
                board[i][j] = game.positionToString(j,i);
            }
        }
    }

    @Override
    public String toString() {
        encodeGame(printerGame);
        int cellSize = 7;
        int marginSize = 2;
        String vDelimiter = "|";
        String hDelimiter = "-";
        String intersect = space;
        String vIntersect = space;
        String hIntersect = "-";
        String corner = space;

        String cellDelimiter = MyStringUtils.repeat(hDelimiter, cellSize);

        String rowDelimiter = vIntersect + MyStringUtils.repeat(cellDelimiter + intersect, numCols-1) + cellDelimiter + vIntersect;
        String hEdge =  corner + MyStringUtils.repeat(cellDelimiter + hIntersect, numCols-1) + cellDelimiter + corner;

        String margin = MyStringUtils.repeat(space, marginSize);
        String lineEdge = String.format("%n%s%s%n", margin, hEdge);
        String lineDelimiter = String.format("%n%s%s%n", margin, rowDelimiter);

        StringBuilder str = new StringBuilder();

        str.append(lineEdge);
        for(int i=0; i<numRows; i++) {
            str.append(margin).append(vDelimiter);
            for (int j=0; j<numCols; j++)
                str.append( MyStringUtils.centre(board[i][j], cellSize)).append(vDelimiter);
            if (i != numRows - 1) str.append(lineDelimiter);
            else str.append(lineEdge);
        }

        return printerGame.infoToString() + str.toString();
    }
}