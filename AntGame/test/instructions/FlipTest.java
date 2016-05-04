package instructions;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author as750
 */
public class FlipTest {
    
    public FlipTest() {
    }

    Flip f;
    
    @Before
    public void SetUpFlip() throws NotValidInstructionException{
        f = new Flip(5, 0, 6);
    }
    
    @Test
    public void GetNTest(){
        assertEquals(5, f.getN());
    }
    
    @Test
    public void SetNTest(){
        assertEquals(5, f.getN());
        f.setN(7);
        assertEquals(7, f.getN());
    }
    
    @Test
    public void GetNis0StateTest(){
        assertEquals(0, f.getNisOState());
    }
    
    @Test
    public void SetNis0State(){
        assertEquals(0, f.getNisOState());
        f.setNisOState(1);
        assertEquals(1, f.getNisOState());
    }
    
    @Test
    public void GetNGTOState(){
        assertEquals(6, f.getnGTOState());
    }
    
    @Test
    public void SetNGTOState(){
        assertEquals(6, f.getnGTOState());
        f.setnGTOState(8);
        assertEquals(8, f.getnGTOState());
    }
}
