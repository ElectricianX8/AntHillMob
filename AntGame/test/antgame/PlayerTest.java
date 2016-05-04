package antgame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import parsers.AntBrain;
import parsers.ParsingException;
import antgame.*;
import instructions.Instruction;
import instructions.NotValidInstructionException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import parsers.LexerException;

/**
 *
 * @author oliverthomas
 */
public class PlayerTest {
    
    public PlayerTest() {
    }
    
    Player p1,p2;
    int id;
    AntBrain ab1, ab2, ab3;
    
    @Before
    public void setUpPlayer(){
        Instruction[] i = new Instruction[10];
        ab1 = new AntBrain("~Brain1", i);
        ab2 = new AntBrain("~Brain2", i);
        ab3 = new AntBrain("~Brain3", i);
        p1 = new Player("Jeremy", ++id, ab1);
        p2 = new Player("James", ++id, ab2);
    }
    
    @Test
    public void getNameTest(){
        assertEquals("Jeremy", p1.getName());
        assertEquals("James", p2.getName());
    }
    
    @Test
    public void setNameTest(){
        assertEquals("Jeremy", p1.getName());
        p1.setName("Martha");
        assertFalse(p1.getName().equals("Jeremy"));
        assertEquals("Martha", p1.getName());
    }
    
    @Test
    public void getIdTest(){
        assertEquals(1, p1.getId());
        assertEquals(2, p2.getId());
    }
    
    @Test
    public void setIdTest(){
        assertEquals(1, p1.getId());
        p1.setId(5);
        assertFalse(p1.getId() == 1);
        assertEquals(5, p1.getId());
    }
    
    @Test
    public void getAntBrainTest(){
        assertEquals(ab1, p1.getAntBrain());
        assertFalse(p1.getAntBrain() == ab2);
        assertEquals(ab2, p2.getAntBrain());
        assertFalse(p2.getAntBrain() == ab1);
    }
    
    @Test
    public void setAntBrainTest(){
        assertEquals(ab1, p1.getAntBrain());
        assertFalse(p1.getAntBrain() == ab2);
        p1.setAntBrain(ab2);
        assertEquals(ab2, p1.getAntBrain());
        assertFalse(p1.getAntBrain() == ab1);
    }
    
    @Test
    public void loadAntBrainTest() throws IOException, LexerException, ParsingException, NotValidInstructionException{
        assertEquals(ab1, p1.getAntBrain());
        p1.loadAntBrain(new File("sampleInvalid.ant"));
        assertFalse(ab1 == p1.getAntBrain());
    }
    
    @Test
    public void lexTest() throws ParsingException, NotValidInstructionException {
        Player p = new Player();
        p.setName("Test Player");
        p.setId(1);
        
        File f = new File("TestBrain.txt");
        try {
            p.loadAntBrain(f);
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
        
        assertNotNull(p.getAntBrain());
        
        //p.saveAntBrain();
        
    }
}
