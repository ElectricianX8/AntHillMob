package antgame;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author as750
 */
public class CellTest {
    
    public CellTest() {
    }

    Cell cell;
    Terrain terrain;
    Colour antHill;
    Ant ant;
    
    @Before
    public void setUp(){
        terrain = Terrain.CLEAR;
        antHill = Colour.BLACK;
        cell = new Cell(terrain, antHill);
        
        ant = new Ant(Colour.RED, 2, 3, 4);
    }
    
    @Test
    public void getTerrainTest(){
        assertEquals(Terrain.CLEAR, cell.getTerrain());
    }
    
    @Test
    public void getFoodCountTest(){
        assertEquals(0, cell.getFoodCount());
    }
    
    @Test
    public void setFoodCountTest(){
        assertEquals(0, cell.getFoodCount());
        cell.setFoodCount(10);
        assertEquals(10, cell.getFoodCount());
    }
    
    @Test
    public void isOccupiedTest(){
        assertFalse(cell.isOccupied());
    }
    
    @Test
    public void setOccupiedTest(){
        assertFalse(cell.isOccupied());
        cell.setOccupied(ant);
        assertTrue(cell.isOccupied());
    }
    
    @Test
    public void clearAntTest(){
        cell.setOccupied(ant);
        assertTrue(cell.isOccupied());
        cell.clearAnt();
        assertFalse(cell.isOccupied());
    }
    
    @Test
    public void getAntTest(){
        cell.setOccupied(ant);
        assertEquals(ant, cell.getAnt());
    }
    
    @Test
    public void MarkersTest(){
        int marker = 4;
        //Initially no marker so checking if marker exists returns false
        assertFalse(cell.findMarker(antHill, marker));
        //Setting marker
        cell.setMarker(antHill, marker);
        //Checking whether a marker is in cell
        assertTrue(cell.findAnyMarker(antHill));
        //Checking whether specific marker set earlier is now in cell
        assertTrue(cell.findMarker(antHill, marker));
        //Clearing marker from cell
        cell.clearMarker(antHill, marker);
        //Checking that cell is now empty of specific marker set earlier
        assertFalse(cell.findMarker(antHill, marker));
        //Checking whether there is any marker left in the cell
        assertFalse(cell.findAnyMarker(antHill));
    }
    
    @Test
    public void isAntHillTest(){
        assertFalse(cell.isAnthill(Colour.RED));
        assertTrue(cell.isAnthill(antHill));
    }
}
