/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

import instructions.NotValidInstructionException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;
import parsers.InvalidMapTokenException;
import parsers.WorldParser;

/**
 *
 * @author as750
 */
public class TournamentTest {
    
    public TournamentTest() {
    }

    @Test
    public void TournamentTest(){
        try {
            WorldParser parser = new WorldParser();
            GameBoard world1 = parser.parse("sample0.world.txt");
            Player one = new Player();
            one.setName("One");
            one.setId(1);
            one.loadAntBrain(new File("sample.ant"));
            Player two = new Player();
            two.setName("Two");
            two.setId(2);
            two.loadAntBrain(new File("sample.ant"));
            Player three = new Player();
            three.setName("Three");
            three.setId(3);
            three.loadAntBrain(new File("sample.ant"));
            Player four = new Player();
            four.setName("Four");
            four.setId(4);
            four.loadAntBrain(new File("sample.ant"));
            ArrayList<Player> playerList = new ArrayList<>();
            playerList.add(one);
            playerList.add(two);
            playerList.add(three);
            playerList.add(four);
            ArrayList<GameBoard> worldList = new ArrayList<>();
            worldList.add(world1);
            GameController gc = new GameController();
            gc.setTournament(playerList, worldList);
            int numberOfMatches = 0;
            for (int i = 0; i < playerList.size() - 1; i++) {
            for (int j = i + 1; j < playerList.size(); j++) {
                for (int w = 0; w < worldList.size(); w++) {
                    numberOfMatches += 2;
                }
            }
        }
            while(numberOfMatches > 0){
                gc.startNextMatch();
                numberOfMatches--;
            }
            System.out.println("Tournament test successful");
            System.out.println("Tournament Leader board:");
            for(int i = 0; i < gc.getLeaderboard().size(); i++){
                System.out.println("Number " + (i+1) + ": " + gc.getLeaderboard().get(i).getName() + " with " + gc.getLeaderboard().get(i).getScore() + " points.");
            }
        } catch (IOException | InvalidMapTokenException | NotValidInstructionException ex) {
            Logger.getLogger(MatchTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(MatchTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
