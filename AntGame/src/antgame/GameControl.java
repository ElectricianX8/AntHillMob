/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

import java.util.ArrayList;

/**
 *
 * Interface for the game, link it with UI
 */
public interface GameControl {
    
    
    
    //return sorted list of players, in descending order, sorted by score, then name
    public ArrayList<Player> getLeaderboard();
    
    //internally set up a single game
    public void setSingleGame( ArrayList<Player> players, ArrayList<GameBoard> worlds);
    
    //internally process a whole queue of games
    public void setTournament( ArrayList<Player> players, ArrayList<GameBoard> worlds);
    
    //return the next match to be played, null if all matches finished
    public QueueMatch getNextMatch();
    
    //run the next match, here delay will be used to control refresh frequency, skip if we dont update display, let the game finish as fast as possible 
    //call updateView(x) in gui from here probably, to update current state of map after x steps
    //call createTournamentPanel() to bring up tournament menu for tournaments after each game 
    //above 2 methods subject to change
    public Result startNextMatch() throws Exception;
    
     //speed up/down refresh rate
    public void changeDelay(int delay);
    
    
    
}
