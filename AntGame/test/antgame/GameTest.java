package antgame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import parsers.InvalidMapTokenException;
import parsers.WorldParser;
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

    /**
     * Initializes the parameters for the Game class, as well as games it depends on.
     */
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
    
    


    /**
     * Tests whether the current coordinate matches the criteria in the condition.
     */
    @Test
    public void cell_matches_test() {
        try {
            Condition cond = new Condition(ConditionType.Home);
            Coordinate cord = new Coordinate(27, 41); //red anthill

            boolean res = game.isCellMatching(cord, cond, Colour.RED);
            assertTrue(res);
        } catch (Exception ex) {
            Logger.getLogger(GameTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Tests the senseDirection method in the Game class, which returns the coordinate of the cell in the 
     * sensed direction.
     */
    @Test
    public void SenseDirectionTest() {
        try {
            Condition cond = new Condition(ConditionType.Home);
            Coordinate cord = new Coordinate(27, 41); //red anthill

            Coordinate testCord = game.getSensedCell(cord, 0, SenseDirection.Ahead);
            assertEquals(42, testCord.getX());
            boolean res = game.isCellMatching(testCord, cond, Colour.RED);
            assertTrue(res);
            testCord = game.getSensedCell(cord, 0, SenseDirection.RightAhead);
            assertEquals(42, testCord.getX());
            assertEquals(28, testCord.getY());
            res = game.isCellMatching(testCord, cond, Colour.RED);
            assertTrue(res);
        } catch (Exception ex) {
            Logger.getLogger(GameTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    /**
     * Tests the adjacentAnts method in the Game class. Gives the number of ants of the specified colour
     * in the cells adjacent to the cell occupied by an ant.
     */
    @Test
    public void adjacentAntsTest(){
        
       
        Coordinate cord = new Coordinate(27, 43); //red anthill
        
        int antTest = game.countAdjacentAnts(cord, Colour.RED);
        assertEquals(4, antTest);
        Coordinate cord2 = new Coordinate(27, 40);
        int antTest2 = game.countAdjacentAnts(cord2, Colour.RED);
        assertEquals(3, antTest2);
        
    }
    
    /**
     * Tests whether the other method that gets the colour of the opposition's ant works in the game class.
     */
    @Test
    public void otherColourTest(){
        assertEquals(Colour.BLACK, game.getOppositeColour(Colour.RED));
    }
    
    /**
     * Tests the turn method in the game class for both when an ant turns left and when an ant
     * turns right.
     */
    @Test
    public void turnTest(){
        assertEquals(3, game.turn(TurnDirection.Left, 4));
        assertEquals(0, game.turn(TurnDirection.Left, 1));
        assertEquals(0, game.turn(TurnDirection.Right, 5));
        assertEquals(2, game.turn(TurnDirection.Right, 7));
    }
    
    
    /**
     * Boolean indicates whether the game has finished. Game not started yet, so cannot finish,
     * therefore we assert that the boolean will return false.
     * @throws Exception 
     */
    @Test
    public void isDoneTest() throws Exception{
        assertFalse(game.isGameDone());
    }
}
