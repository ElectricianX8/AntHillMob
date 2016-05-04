package antgame;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author as750
 */
public class CoordinateTest {
    
    public CoordinateTest() {
    }
    
    int x;
    int y;
    Coordinate c;

    @Before
    public void setupCoordinate(){
        x = 5;
        y = 7;
        c = new Coordinate(y,x);
    }
    
    @Test
    public void getXTest(){
        assertEquals(x, c.getX());
    }
    
    @Test
    public void setXTest(){
        assertEquals(x, c.getX());
        c.setX(10);
        assertFalse(c.getX() == x);
        assertEquals(10, c.getX());
    }
    
    @Test
    public void getYTest(){
        assertEquals(y, c.getY());
    }
    
    @Test
    public void setYTest(){
        assertEquals(y, c.getY());
        c.setY(14);
        assertFalse(c.getY() == y);
        assertEquals(14, c.getY());
    }
}
