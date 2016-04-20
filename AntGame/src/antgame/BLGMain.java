package antgame;

import UI.GameView;
import java.io.IOException;

/**
 *
 * Main
 */
public class BLGMain {

    public static void main(String[] args) {
        /*
         BoardLayoutGenerator blg = new BoardLayoutGenerator();
         try {
         blg.generate(123, 30, 1, 5, true, 99);
         blg.out();
         } catch (Exception ex) {
         System.out.println("parameters meant that board couldn't be generated");
         System.out.println("reason: " + ex.getMessage());
         }
         */

        
        WorldParser parser = new WorldParser();
        
        try {
            GameBoard testBoard = parser.parse("1.world");
            testBoard.printBoard();
            
            GameView ui = new GameView();
            //ui.updateView(testBoard);
            
            
        } catch (IOException ex) {
            System.out.println("IO Exception");
        } catch (InvalidMapTokenException ex) {
            System.out.println("Invalid token");
        }
        
        //GameBoard board = new GameBoard(10,10, null);
        //System.out.println(board.rocky(new Coordinate(0,0)));
        //System.out.println(board.some_ant_is_at(new Coordinate(0,0)));
        


    }

}
