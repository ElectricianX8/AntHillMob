package antgame;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author as750
 */
public class AntTest {
    
    public AntTest() {
    }
    
    Ant a, b;
    
    @Before
    public void createAnt(){
        Colour colour = Colour.BLACK;
        int direction = 5;
        int x = 6;
        int y = 5;
        a = new Ant(colour, direction, x, y);
        colour = Colour.RED;
        direction = 2;
        x = 2;
        y = 4;
        b = new Ant(colour, direction, x, y);
    }

    @Test
    public void getIDTest() {
        assertEquals(1, a.getId());
        assertEquals(2, b.getId());
    }
    
    @Test
    public void getColourTest(){
        assertEquals(Colour.BLACK, a.getColour());
        assertEquals(Colour.RED, b.getColour());
    }
    
    @Test
    public void getStateTest(){
        assertEquals(0, a.getState());
    }
    
    @Test
    public void setStateTest(){
        assertEquals(0, a.getState());
        a.setState(55);
        assertEquals(55, a.getState());
    }
    
    @Test
    public void getRestingTest(){
        assertEquals(0, a.getResting());
    }
    
    @Test
    public void setRestingTest(){
        assertEquals(0, a.getResting());
        a.setResting(100);
        assertEquals(100, a.getResting());
    }
    
    @Test
    public void decRestingTest(){
        a.setResting(100);
        assertEquals(100, a.getResting());
        a.decResting();
        assertEquals(99, a.getResting());
        a.decResting();
        assertEquals(98, a.getResting());
    }
    
    @Test
    public void getDirectionTest(){
        assertEquals(5, a.getDirection());
        assertEquals(2, b.getDirection());
    }
    
    @Test
    public void setDirectionTest(){
        assertEquals(5, a.getDirection());
        a.setDirection(3);
        assertEquals(3, a.getDirection());
    }
    
    @Test
    public void hasFoodTest(){
        assertFalse(a.hasFood());
    }
    
    @Test
    public void setHasFoodTest(){
        assertFalse(a.hasFood());
        a.setHasFood(true);
        assertTrue(a.hasFood());
    }
    
    @Test
    public void getCuurentPositionTest(){
        Coordinate expectedCoordinate1 = new Coordinate(6,5);
        Coordinate expectedCoordinate2 = new Coordinate(2,4);
        
        assertEquals(expectedCoordinate1.getX(), a.getCurrentPosition().getX());
        assertEquals(expectedCoordinate1.getY(), a.getCurrentPosition().getY());
        
        assertEquals(expectedCoordinate2.getX(), b.getCurrentPosition().getX());
        assertEquals(expectedCoordinate2.getY(), b.getCurrentPosition().getY());
    }
    
    @Test
    public void getIsAliveTest(){
        assertTrue(a.IsAlive());
    }
    
    @Test
    public void setIsAliveTest(){
        assertTrue(a.IsAlive());
        a.setIsAlive(false);
        assertFalse(a.IsAlive());
    }
    
    @Test
    public void setCurrentPositionTest(){
        Coordinate expectedCoordinate1 = new Coordinate(6,5);
        Coordinate expectedCoordinate2 = new Coordinate(2,4);
        
        assertEquals(expectedCoordinate1.getX(), a.getCurrentPosition().getX());
        assertEquals(expectedCoordinate1.getY(), a.getCurrentPosition().getY());
        
        a.setCurrentPosition(expectedCoordinate2);
        
        assertEquals(expectedCoordinate2.getX(), a.getCurrentPosition().getX());
        assertEquals(expectedCoordinate2.getY(), a.getCurrentPosition().getY());
    }
}