package antgame;

import instructions.*;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author as750
 */
public class AntBrainTest {
    
    public AntBrainTest() {
    }
    
    String name;
    String nospace;
    Instruction[] brain;
    AntBrain ab;
    
    @Before
    public void createAntBrain() {
        name = " Monica";
        nospace = "Monica";
        brain = new Instruction[4];
        brain[0] = new Drop();
        brain[1] = new Move();
        brain[2] = new Mark();
        brain[3] = new Flip();
        ab = new AntBrain(name, brain);
    }

    @Test
    public void getNameRawTest(){
        assertEquals(name, ab.getNameRaw());
    }
    
    @Test
    public void getNameTest(){
        assertEquals(nospace, ab.getName());
    }
    
    @Test
    public void getInstructionAtTest(){
        assertTrue(ab.getInstructionAt(0) instanceof Drop);
        assertTrue(ab.getInstructionAt(1) instanceof Move);
        assertTrue(ab.getInstructionAt(2) instanceof Mark);
        assertTrue(ab.getInstructionAt(3) instanceof Flip);
    }
    
    @Test
    public void getInstructionArrayTest(){
        Assert.assertArrayEquals(brain, ab.getInstructionArray());
    }
}
