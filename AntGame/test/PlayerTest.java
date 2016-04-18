/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import antgame.*;
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

/**
 *
 * @author oliverthomas
 */
public class PlayerTest {
    
    public PlayerTest() {
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
        
        p.saveAntBrain();
        
    }
}
