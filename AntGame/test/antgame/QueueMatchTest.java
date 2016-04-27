
package antgame;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author as750
 */
public class QueueMatchTest {
    
    public QueueMatchTest() {
    }

    QueueMatch qm;
    Player red, black;
    GameBoard board;
    int mapNum;
    
    @Before
    public void setUp(){
        red = new Player();
        black = new Player();
        board = new GameBoard(50, 50);
        mapNum = 5;
        qm = new QueueMatch(red, black, board, mapNum);
    }
    
    @Test
    public void getRedPlayerTest(){
        assertEquals(red, qm.getRedPlayer());
        assertFalse(black == qm.getRedPlayer());
    }
    
    @Test
    public void getBlackPlayerTest(){
        assertEquals(black, qm.getBlackPlayer());
        assertFalse(red == qm.getBlackPlayer());
    }
    
    @Test
    public void getGameBoardTest(){
        assertEquals(board, qm.getGameBoard());
    }
    
    @Test
    public void getMapNumber(){
        assertEquals(mapNum, qm.getMapNumber());
    }
}
