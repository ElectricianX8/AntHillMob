package antgame;

import instructions.NotValidInstructionException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import parsers.InvalidMapTokenException;
import parsers.LexerException;
import parsers.ParsingException;
import parsers.WorldParser;

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
        try {
            assertFalse(r.gameWasATie());
            WorldParser parser = new WorldParser();
            Player one = new Player();
            one.setName("Dave");
            one.setId(1);
            one.loadAntBrain(new File("sample.ant"));
            Player two = new Player();
            two.setName("Chris");
            two.setId(2);
            two.loadAntBrain(new File("sample.ant"));
            GameBoard board = parser.parse("sample0.world.txt");
            Game g = new Game(one, two, board);
            Result r = g.start();
            if(g.getPlayerFoodCount(Colour.RED) == g.getPlayerFoodCount(Colour.BLACK)){
                System.out.println(g.getPlayerFoodCount(Colour.RED));
                System.out.println(g.getPlayerFoodCount(Colour.BLACK));
                assertTrue(r.gameWasATie());
            }
            else{
                assertFalse(r.gameWasATie());
                if(g.getPlayerFoodCount(Colour.RED) > g.getPlayerFoodCount(Colour.BLACK)){
                    assertEquals(one, r.getWinner());
                    assertEquals(two, r.getLoser());
                }
                else{
                    assertEquals(two, r.getWinner());
                    assertEquals(one, r.getLoser());
                }
            }
            
                    } catch (IOException ex) {
            Logger.getLogger(ResultTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidMapTokenException ex) {
            Logger.getLogger(ResultTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LexerException ex) {
            Logger.getLogger(ResultTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParsingException ex) {
            Logger.getLogger(ResultTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotValidInstructionException ex) {
            Logger.getLogger(ResultTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ResultTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
