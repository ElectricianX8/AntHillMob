package antgame;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author as750
 */
public class ResultTest {
    
    public ResultTest() {
    }

    Result r;
    Player red, black;

    @Before
    public void setUpResults(){
        red = new Player();
        black = new Player();
        r = new Result(red, black, false);
    }
    
    @Test
    public void getMatchNumTest(){
        assertEquals(1, r.getMatchNum());
    }
    
    @Test
    public void getWinnerTest(){
        assertEquals(red, r.getWinner());
        assertFalse(black == r.getWinner());
    }
    
    @Test
    public void getLoserTest(){
        assertEquals(black, r.getLoser());
        assertFalse(red == r.getLoser());
    }
    
    @Test
    public void gameWasATieTest(){
        assertFalse(r.gameWasATie());
    }
}
