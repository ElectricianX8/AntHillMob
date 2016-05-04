package instructions;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author as750
 */
public class ConditionTest {
    
    public ConditionTest() {
    }

    Condition c = new Condition(ConditionType.Friend);
    
    @Test
    public void getConditionTest(){
        assertEquals(ConditionType.Friend, c.getCondition());
    }
    
    @Test
    public void getMark(){
        assertEquals(0, c.getMark());
    }
}
