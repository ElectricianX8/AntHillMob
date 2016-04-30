/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package antgame;

/**
 *
 * Represents a match to be played.
 */
public class QueueMatch {
    
    private Player redPlayer;
    private Player blackPlayer;
    private GameBoard board;
    private int mapNumber;
    
    /**
     * Constructor for the queued match.
     * @param redPlayer Red Player.
     * @param blackPlayer Black Player.
     * @param board The board/world to play on.
     * @param mapNumber Number of the board/world to play on.
     */
    public QueueMatch(Player redPlayer, Player blackPlayer, GameBoard board, int mapNumber){
        this.redPlayer = redPlayer;
        this.blackPlayer = blackPlayer;
        this.board = board;
        this.mapNumber = mapNumber;
    }
    
    /**
     * Returns the player playing as red.
     * @return The player playing as red.
     */
    public Player getRedPlayer(){
        return redPlayer;
    }
    
    /**
     Returns the player playing as black.
     * @return The player playing as black.
     */
    public Player getBlackPlayer(){
        return blackPlayer;
    }
    
    /**
     * Returns the board/world to be played on.
     * @return The board/world to be played on.
     */
    public GameBoard getGameBoard(){
        return board;
    }
    
    /**
     * Returns the number of the board/world to be played on.
     * @return The number of the board/world to be played on.
     */
    public int getMapNumber(){
        return mapNumber;
    }
    
    @Override
    public String toString(){
        String msg = "Red: " + redPlayer.getName() + ", Black: " + blackPlayer.getName() + " Board: " + board;
        return msg;
    }

}
