package instructions;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author as750
 */
public class MarkTest {
    
    public MarkTest() {
    }

    Mark m = new Mark(4 ,6);
    
    @Test
    public void getMarkToLeaveTest(){
        assertEquals(4, m.getMarkToLeave());
    }
    
    @Test
    public void setMarkToLeaveTest(){
        assertEquals(4, m.getMarkToLeave());
        m.setMarkToLeave(9);
        assertEquals(9, m.getMarkToLeave());
    }
    
    @Test
    public void getStateToGoToTest(){
        assertEquals(6, m.getStateToGoTo());
    }
    
    @Test
    public void setStateToGoToTest(){
        assertEquals(6, m.getStateToGoTo());
        m.setStateToGoTo(3);
        assertEquals(3, m.getStateToGoTo());
    }
}
