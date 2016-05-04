/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instructions;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author as750
 */
public class SenseTest {
    
    public SenseTest() {
    }

    Condition c = new Condition(ConditionType.Foe);
    Sense s = new Sense(SenseDirection.Ahead, 1, 2, c);
    
    @Test
    public void getSenseDirectionTest(){
        assertEquals(SenseDirection.Ahead, s.getSenseDirection());
    }
    
    @Test
    public void setSenseDirectionTest(){
        assertEquals(SenseDirection.Ahead, s.getSenseDirection());
        s.setSenseDirection(SenseDirection.RightAhead);
        assertEquals(SenseDirection.RightAhead, s.getSenseDirection());
    }
    
    @Test
    public void getTrueStateTest(){
        assertEquals(1, s.getTrueState());
    }
    
    @Test
    public void setTrueStateTest(){
        assertEquals(1, s.getTrueState());
        s.setTrueState(6);
        assertEquals(6, s.getTrueState());
    }
    
    @Test
    public void getFalseStateTest(){
        assertEquals(2, s.getFalseState());
    }
    
    @Test
    public void setFalseStateTest(){
        assertEquals(2, s.getFalseState());
        s.setFalseState(1);
        assertEquals(1, s.getFalseState());
    }
    
    @Test
    public void getCondTest(){
        assertEquals(c, s.getCond());
    }
    
    @Test
    public void setCondTest(){
        assertEquals(c, s.getCond());
        Condition c2 = new Condition(ConditionType.Friend);
        s.setCond(c2);
        assertEquals(c2, s.getCond());
    }
}
