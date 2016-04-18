/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import antgame.Ant;
import antgame.Colour;
import antgame.Coordinate;
import antgame.GameBoard;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 *
 */
public class GameBoardTest {

    public GameBoardTest() {
    }

    @Test
    public void MarkerAddingTest() {
        try {
            GameBoard board = new GameBoard(10, 10, null);
            Coordinate cell = new Coordinate(0, 0);
            board.set_marker_at(cell, Colour.RED, 1);
            assertTrue(board.check_marker_at(cell, Colour.RED, 1));

            board.clear_marker_at(cell, Colour.RED, 0);
            assertTrue(board.check_marker_at(cell, Colour.RED, 1));

            board.clear_marker_at(cell, Colour.RED, 1);
            assertFalse(board.check_marker_at(cell, Colour.RED, 1));
        } catch (Exception ex) {
            Logger.getLogger(GameBoardTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void MarkerDuplicateTest() {
        try {
            GameBoard board = new GameBoard(10, 10, null);
            Coordinate cell = new Coordinate(0, 0);
            board.set_marker_at(cell, Colour.RED, 1);
            //make sure it only adds one, not multiple of same marker
            assertTrue(board.check_marker_at(cell, Colour.RED, 1));

            board.set_marker_at(cell, Colour.RED, 1);
            assertTrue(board.check_marker_at(cell, Colour.RED, 1));

            board.clear_marker_at(cell, Colour.RED, 1);
            //assertTrue(board.check_marker_at(cell, Colour.RED, 1));

            assertFalse(board.check_any_marker_at(cell, Colour.RED));
        } catch (Exception ex) {
            Logger.getLogger(GameBoardTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void MarkerMultipleSetsTest() {

        GameBoard board = new GameBoard(10, 10, null);
        Coordinate cell = new Coordinate(0, 0);
        board.set_marker_at(cell, Colour.RED, 1);
        board.set_marker_at(cell, Colour.BLACK, 1);
        board.clear_marker_at(cell, Colour.RED, 1);
        assertFalse(board.check_any_marker_at(cell, Colour.RED));
        assertTrue(board.check_any_marker_at(cell, Colour.BLACK));
    }

    @Test
    public void AddingDifferentMarkersTest() {
        try {
            // check adding different markers
            GameBoard board = new GameBoard(10, 10, null);
            Coordinate cell = new Coordinate(0, 0);
            board.set_marker_at(cell, Colour.RED, 1);
            board.set_marker_at(cell, Colour.RED, 2);
            board.clear_marker_at(cell, Colour.RED, 1);
            assertTrue(board.check_any_marker_at(cell, Colour.RED));
            assertTrue(board.check_marker_at(cell, Colour.RED, 2));
            assertFalse(board.check_marker_at(cell, Colour.RED, 1));
        } catch (Exception ex) {
            Logger.getLogger(GameBoardTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void AnthillCheckTest() {

        GameBoard board = new GameBoard(10, 10, null);
        Coordinate cell = new Coordinate(0, 0);
        assertFalse(board.anthill_at(cell, Colour.RED));
        assertFalse(board.anthill_at(cell, Colour.BLACK));
        assertTrue(board.anthill_at(cell, null));
    }

    @Test
    public void foodCountTest() {
        GameBoard board = new GameBoard(10, 10, null);
        Coordinate cell = new Coordinate(0, 0);
        board.set_food_at(cell, 10);
        assertEquals(10, board.food_at(cell));
        board.set_food_at(cell, 25);
        assertEquals(25, board.food_at(cell));
    }
    
    
    @Test
    public void manageAntsTest(){
        ArrayList<Ant> ants = new ArrayList<Ant>();
        Ant ant = new Ant(Colour.BLACK, 5, 0,0);
        Ant ant2 = new Ant(Colour.RED, 5, 0,1);
        Ant ant3 = new Ant(Colour.RED, 5, 1,1);
        ants.add(ant);
        ants.add(ant2);
        ants.add(ant3);
        
        
        //static id in ants may cause some test errors, probably due to static not resetting upon test retakes sometimes
        GameBoard board = new GameBoard(10, 10, ants);
        Coordinate cell = new Coordinate(0, 0);
        Coordinate cell2 = new Coordinate(0, 1);
        Coordinate cell3 = new Coordinate(1, 1);
        board.set_ant_at(cell, ant);
        board.set_ant_at(cell2, ant2);
        board.set_ant_at(cell3, ant3);
        assertEquals(1, board.ant_at(cell).getId());
        assertTrue(board.ant_is_alive(1));
        board.clear_ant_at(cell);//clear cell
        assertTrue(board.ant_is_alive(1)); //ant still lives elsewhere
        board.kill_ant_at(cell2); // kill ant
        assertFalse(board.ant_is_alive(2)); //ant not alive anymore
        assertTrue(board.ant_is_alive(1));
        assertTrue(board.ant_is_alive(3));
        assertEquals(0, board.find_ant(1).getX());
        assertEquals(0, board.find_ant(1).getY());
        assertEquals(1, board.find_ant(3).getX());
        assertEquals(1, board.find_ant(3).getY());
        assertFalse(board.some_ant_is_at(cell));
        assertFalse(board.some_ant_is_at(cell2));
        assertTrue(board.some_ant_is_at(cell3));
        
        
        
    }
    
    
    
    @Test
    public void AdjacentCellTest(){
        
        ArrayList<Ant> ants = new ArrayList<Ant>();
        Ant ant = new Ant(Colour.BLACK, 5, 0,0);
        Ant ant2 = new Ant(Colour.RED, 5, 0,1);
        Ant ant3 = new Ant(Colour.RED, 5, 1,1);
        ants.add(ant);
        ants.add(ant2);
        ants.add(ant3);
        
        GameBoard board = new GameBoard(10, 10, ants);
        Coordinate cell = new Coordinate(0, 0);
        Coordinate cell2 = new Coordinate(0, 1);
        Coordinate cell3 = new Coordinate(1, 1);
        
        int testX, testY;
        
        Coordinate testCord = board.adjacent_cell(cell, 0);
        testX = testCord.getX();
        testY = testCord.getY();
        assertEquals(1, testX);
        assertEquals(0, testY);
        
        testCord = board.adjacent_cell(cell, 1);
        testX = testCord.getX();
        testY = testCord.getY();
        assertEquals(0, testX);
        assertEquals(1, testY);
        
        
        testCord = board.adjacent_cell(cell, 5);
        assertEquals(null, testCord);
        
        testCord = board.adjacent_cell(cell, 4);
        assertEquals(null, testCord);
        
        testCord = board.adjacent_cell(cell, 3);
        assertEquals(null, testCord);
        
        testCord = board.adjacent_cell(cell, 2);
        assertEquals(null, testCord);
        
        testCord = board.adjacent_cell(cell3, 3);
        testX = testCord.getX();
        testY = testCord.getY();
        assertEquals(0, testX);
        assertEquals(1, testY);
        
        
        testCord = board.adjacent_cell(cell3, 4);
        testX = testCord.getX();
        testY = testCord.getY();
        assertEquals(1, testX);
        assertEquals(0, testY); //check here, not sure what even is meant to do here
        
    }
    
   

}
