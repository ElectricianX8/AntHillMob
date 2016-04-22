/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import antgame.Ant;
import antgame.Game;
import antgame.GameBoard;
import antgame.InvalidMapTokenException;
import antgame.LexerException;
import antgame.ParsingException;
import antgame.Player;
import antgame.Result;
import antgame.WorldParser;
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
public class MatchTest {

    public MatchTest() {
    }
/*
    @Test
    public void testMatch() throws LexerException, ParsingException {
        try {
            WorldParser parser = new WorldParser();
            GameBoard testBoard = parser.parse("sample0.world.txt");
            Player one = new Player();
            one.setName("One");
            one.setId(1);
            one.loadAntBrain(new File("sample.ant"));
            Player two = new Player();
            two.setName("Two");
            two.setId(2);
            two.loadAntBrain(new File("sample.ant"));
            Match m = new Match(one, one, testBoard);
            Result r = m.start();
            int count = 0;
            for (Ant a : m.getAntArray()) {
                if (a.getIsAlive()) {
                    count++;
                }
            }
            testBoard.printBoard();
            System.out.println("Num of alive ants at the end " + count);
            if (r.gameWasATie()) {
                System.out.println("Tie");
            } else {
                System.out.println("Winner is " + r.getWinner().getName());
            }
        } catch (IOException | InvalidMapTokenException | NotValidInstructionException ex) {
            Logger.getLogger(MatchTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MatchTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
     @Test
     public void testRNG() throws InvalidMapTokenException {
     try {
     WorldParser parser = new WorldParser();
     GameBoard testBoard = parser.parse("sample0.world.txt");
     testBoard.printBoard();
     Player one = new Player();
     one.setName("One");
     one.setId(1);
     one.loadAntBrain(new File("TestBrain.txt"));
     Player two = new Player();
     two.setName("Two");
     two.setId(2);
     two.loadAntBrain(new File("TestBrain.txt"));
     Game m = new Game(one, one, testBoard);
     assertEquals(7193, m.randomNumberGen(16384));
     assertEquals(2932, m.randomNumberGen(16384));
     assertEquals(10386, m.randomNumberGen(16384));
     assertEquals(5575, m.randomNumberGen(16384));
     assertEquals(100, m.randomNumberGen(16384));
     assertEquals(15976, m.randomNumberGen(16384));
     assertEquals(430, m.randomNumberGen(16384));
     assertEquals(9740, m.randomNumberGen(16384));
     assertEquals(9449, m.randomNumberGen(16384));
     assertEquals(1636, m.randomNumberGen(16384));
     assertEquals(11030, m.randomNumberGen(16384));
     assertEquals(9848, m.randomNumberGen(16384));
     assertEquals(13965, m.randomNumberGen(16384));
     assertEquals(16051, m.randomNumberGen(16384));
     assertEquals(14483, m.randomNumberGen(16384));
     assertEquals(6708, m.randomNumberGen(16384));
     assertEquals(5184, m.randomNumberGen(16384));
     assertEquals(15931, m.randomNumberGen(16384));
     assertEquals(7014, m.randomNumberGen(16384));
     assertEquals(461, m.randomNumberGen(16384));
     assertEquals(11371, m.randomNumberGen(16384));
     assertEquals(5856, m.randomNumberGen(16384));
     assertEquals(2136, m.randomNumberGen(16384));
     assertEquals(9139, m.randomNumberGen(16384));
     assertEquals(1684, m.randomNumberGen(16384));
     assertEquals(15900, m.randomNumberGen(16384));
     assertEquals(10236, m.randomNumberGen(16384));
     assertEquals(13297, m.randomNumberGen(16384));
     
     
     
     
     
     
     
     
     
     } catch (IOException | LexerException | ParsingException | NotValidInstructionException ex) {
     Logger.getLogger(MatchTest.class.getName()).log(Level.SEVERE, null, ex);
     }
            
     }
}
