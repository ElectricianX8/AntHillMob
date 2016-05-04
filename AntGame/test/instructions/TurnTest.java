package instructions;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author as750
 */
public class TurnTest {
    
    public TurnTest() {
    }
    
    Turn t = new Turn(TurnDirection.Right, 5);
    
    @Test
    public void getTurnDirectionTest(){
        assertEquals(TurnDirection.Right, t.getTurnDirection());
    }
    
    @Test
    public void setTurnDirectionTest(){
        assertEquals(TurnDirection.Right, t.getTurnDirection());
        t.setTurnDirection(TurnDirection.Left);
        assertEquals(TurnDirection.Left, t.getTurnDirection());
    }
    
    @Test
    public void getStateToGoToTest(){
        assertEquals(5, t.getStateToGoTo());
    }
    
    @Test
    public void setStateToGoToTest(){
        assertEquals(5, t.getStateToGoTo());
        t.setStateToGoTo(3);
        assertEquals(3, t.getStateToGoTo());
    }
}
