package antgame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import antgame.*;
import instructions.Condition;
import instructions.ConditionType;
import instructions.SenseDirection;
import instructions.TurnDirection;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            Player placeHolderPlayer = new Player();
            Player placeHolderPlayer2 = new Player();
            WorldParser parser = new WorldParser();
            GameBoard testBoard = parser.parse("sample0.world.txt");
            game = new Game(placeHolderPlayer, placeHolderPlayer2, testBoard);

        } catch (IOException ex) {
            System.out.println("IO Exception");
        } catch (InvalidMapTokenException ex) {
            System.out.println("Invalid token");
        }
    }
    
    


    @Test
    public void cell_matches_test() {
        try {
            Condition cond = new Condition(ConditionType.Home);
            Coordinate cord = new Coordinate(27, 41); //red anthill

            boolean res = game.cell_matches(cord, cond, Colour.RED);
            assertTrue(res);
        } catch (Exception ex) {
            Logger.getLogger(GameTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Test
    public void SenseDirectionTest() {
        try {
            Condition cond = new Condition(ConditionType.Home);
            Coordinate cord = new Coordinate(27, 41); //red anthill

            Coordinate testCord = game.sensed_cell(cord, 0, SenseDirection.Ahead);
            assertEquals(42, testCord.getX());
            boolean res = game.cell_matches(testCord, cond, Colour.RED);
            assertTrue(res);
            testCord = game.sensed_cell(cord, 0, SenseDirection.RightAhead);
            assertEquals(42, testCord.getX());
            assertEquals(28, testCord.getY());
            res = game.cell_matches(testCord, cond, Colour.RED);
            assertTrue(res);
        } catch (Exception ex) {
            Logger.getLogger(GameTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    @Test
    public void adjacentAntsTest(){
        
       
        Coordinate cord = new Coordinate(27, 43); //red anthill
        
        int antTest = game.adjacent_ants(cord, Colour.RED);
        assertEquals(4, antTest);
        
        System.out.println("works?");
        Coordinate cord2 = new Coordinate(27, 40);
        int antTest2 = game.adjacent_ants(cord2, Colour.RED);
        assertEquals(3, antTest2);
        
    }
    
    @Test
    public void otherColourTest(){
        assertEquals(Colour.BLACK, game.other_color(Colour.RED));
    }
    
    @Test
    public void turnTest(){
        assertEquals(3, game.turn(TurnDirection.Left, 4));
        assertEquals(0, game.turn(TurnDirection.Left, 1));
        assertEquals(0, game.turn(TurnDirection.Right, 5));
        assertEquals(2, game.turn(TurnDirection.Right, 7));
    }
}
