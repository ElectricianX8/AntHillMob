/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package antgame;

/**
 *
 * Wrapper for tournament matches
 */
public class QueueMatch {
    
    Player redPlayer;
    Player blackPlayer;
    GameBoard board;
    
    
    public QueueMatch(Player redPlayer, Player blackPlayer, GameBoard board){
        this.redPlayer = redPlayer;
        this.blackPlayer = blackPlayer;
        this.board = board;
    }
    
    public Player getRedPlayer(){
        return redPlayer;
    }
    
    public Player getBlackPlayer(){
        return blackPlayer;
    }
    
    public GameBoard getGameBoard(){
        return board;
    }
    
    @Override
    public String toString(){
        String msg = "Red: " + redPlayer.getName() + ", Black: " + blackPlayer.getName() + " Board: " + board;
        return msg;
    }

}
