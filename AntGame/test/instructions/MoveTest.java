package instructions;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author as750
 */
public class MoveTest {
    
    public MoveTest() {
    }

    Move m = new Move(3, 5);
    
    @Test
    public void getStateToGoToIfClearTest(){
        assertEquals(3, m.getStateToGoToIfClear());
    }
    
    @Test
    public void setStateToGoToIfClearTest(){
        assertEquals(3, m.getStateToGoToIfClear());
        m.setStateToGoToIfClear(7);
        assertEquals(7, m.getStateToGoToIfClear());
    }
    
    @Test
    public void getStateToGoToIfBlockedTest(){
        assertEquals(5, m.getStateToGoToIfBlocked());
    }
    
    @Test
    public void setStateToGoToIfBlockedTest(){
        assertEquals(5, m.getStateToGoToIfBlocked());
        m.setStateToGoToIfBlocked(2);
        assertEquals(2, m.getStateToGoToIfBlocked());
    }
}
