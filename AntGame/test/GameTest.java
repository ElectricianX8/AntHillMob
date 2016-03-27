/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import antgame.*;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 *
 */
public class GameTest {
    
    public GameTest() {
    }

    
    @Test
    public void cell_matches_test() {
    
    
        WorldParser parser = new WorldParser();
        Condition cond = new Condition(ConditionType.HOME);
        Coordinate cord = new Coordinate(27, 41); //red anthill
        
        
        try {
            
            GameBoard testBoard = parser.parse("sample0.world.txt");
            Game game = new Game(testBoard);
            boolean res = game.cell_matches(cord, cond, Colour.RED);
            assertTrue(res);
            
            
        } catch (IOException ex) {
            System.out.println("IO Exception");
        } catch (InvalidMapTokenException ex) {
            System.out.println("Invalid token");
        }
        
        
    
    
    }
}
