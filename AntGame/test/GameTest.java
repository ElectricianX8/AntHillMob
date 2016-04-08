/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import antgame.*;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 *
 */
public class GameTest {

    Game game;

    public GameTest() {
    }

    @Before
    public void init() {

        try {
            WorldParser parser = new WorldParser();
            GameBoard testBoard = parser.parse("sample0.world.txt");
            game = new Game(testBoard);

        } catch (IOException ex) {
            System.out.println("IO Exception");
        } catch (InvalidMapTokenException ex) {
            System.out.println("Invalid token");
        }
    }
    
    


    @Test
    public void cell_matches_test() {

        Condition cond = new Condition(ConditionType.HOME);
        Coordinate cord = new Coordinate(27, 41); //red anthill

        boolean res = game.cell_matches(cord, cond, Colour.RED);
        assertTrue(res);

    }

    @Test
    public void SenseDirectionTest() {

        Condition cond = new Condition(ConditionType.HOME);
        Coordinate cord = new Coordinate(27, 41); //red anthill

        Coordinate testCord = game.sensed_cell(cord, 0, SenseDirection.AHEAD);
        assertEquals(28, testCord.getX());
        boolean res = game.cell_matches(testCord, cond, Colour.RED);
        assertTrue(res);
        testCord = game.sensed_cell(cord, 0, SenseDirection.RIGHTAHEAD);
        assertEquals(42, testCord.getY());
        assertEquals(28, testCord.getX());
        res = game.cell_matches(testCord, cond, Colour.RED);
        assertTrue(res);

    }
    
    
    @Test
    public void adjacentAntsTest(){
        
        Condition cond = new Condition(ConditionType.HOME);
        Coordinate cord = new Coordinate(27, 41); //red anthill
        
        int antTest = game.adjacent_ants(cord, Colour.RED);
        assertEquals(5, antTest);
        
        Coordinate cord2 = new Coordinate(27, 40);
        int antTest2 = game.adjacent_ants(cord2, Colour.RED);
        assertEquals(2, antTest2);
        
    }
    
}
