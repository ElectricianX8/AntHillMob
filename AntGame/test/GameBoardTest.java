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

        GameBoard board = new GameBoard(10, 10, null);
        Coordinate cell = new Coordinate(0, 0);
        board.set_marker_at(cell, Colour.RED, 1);
        assertTrue(board.check_marker_at(cell, Colour.RED, 1));

        board.clear_marker_at(cell, Colour.RED, 0);
        assertTrue(board.check_marker_at(cell, Colour.RED, 1));

        board.clear_marker_at(cell, Colour.RED, 1);
        assertFalse(board.check_marker_at(cell, Colour.RED, 1));
    }

    @Test
    public void MarkerDuplicateTest() {

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

        // check adding different markers
        GameBoard board = new GameBoard(10, 10, null);
        Coordinate cell = new Coordinate(0, 0);
        board.set_marker_at(cell, Colour.RED, 1);
        board.set_marker_at(cell, Colour.RED, 2);
        board.clear_marker_at(cell, Colour.RED, 1);
        assertTrue(board.check_any_marker_at(cell, Colour.RED));
        assertTrue(board.check_marker_at(cell, Colour.RED, 2));
        assertFalse(board.check_marker_at(cell, Colour.RED, 1));
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
    public void manageAnts(){
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

}
