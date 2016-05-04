package instructions;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author as750
 */
public class UnmarkTest {
    
    public UnmarkTest() {
    }

    Unmark u = new Unmark(4, 5);
    
    @Test
    public void getMarkToRemoveTest(){
        assertEquals(4, u.getMarkToRemove());
    }
    
    @Test
    public void setMarkToRemoveTest(){
        assertEquals(4, u.getMarkToRemove());
        u.setMarkToRemove(7);
        assertEquals(7, u.getMarkToRemove());
    }
    
    @Test
    public void getStateToGoToTest(){
        assertEquals(5, u.getStateToGoTo());
    }
    
    @Test
    public void setStateToGoToTest(){
        assertEquals(5, u.getStateToGoTo());
        u.setStateToGoTo(3);
        assertEquals(3, u.getStateToGoTo());
    }
}
