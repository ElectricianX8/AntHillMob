package instructions;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author as750
 */
public class DropTest {
    
    public DropTest() {
    }

    Drop d = new Drop(5);
    
    @Test
    public void getStateToGoToTest(){
        assertEquals(5, d.getStateToGoTo());
    }
    
    @Test
    public void setStateToGoToTest(){
        assertEquals(5, d.getStateToGoTo());
        d.setStateToGoTo(3);
        assertEquals(3, d.getStateToGoTo());
    }
}
