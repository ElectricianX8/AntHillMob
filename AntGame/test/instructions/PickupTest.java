package instructions;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author as750
 */
public class PickupTest {
    
    public PickupTest() {
    }

    Pickup p = new Pickup(1, 2);
    
    @Test
    public void getTrueState(){
        assertEquals(1, p.getTrueState());
    }
    
    @Test
    public void setTrueState(){
        assertEquals(1, p.getTrueState());
        p.setTrueState(3);
        assertEquals(3, p.getTrueState());
    }
    
    @Test
    public void getFalseState(){
        assertEquals(2, p.getFalseState());
    }
    
    @Test
    public void setFalseState(){
        assertEquals(2, p.getFalseState());
        p.setFalseState(5);
        assertEquals(5, p.getFalseState());
    }
}
