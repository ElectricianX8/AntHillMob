/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokens;

/**
 *
 * Token representing a turn
 * 
 */
public class TokenTurn extends Token {
    String turn;
    
    /**
     * Constructor
     * @param turn The turn
     */
    public TokenTurn(String turn) {
        this.turn = turn.trim();
    }
    
    /**
     * Returns the turn of the token.
     * @return The turn of the token.
     */
    public String getTurn() {
        return turn;
    }
    @Override
    public String toString() {
        return new String(turn);
    }
}
