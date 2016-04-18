/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokens;

/**
 *
 * @author oliverthomas
 */
public class TokenTurn extends Token {
    String turn;
    
    public TokenTurn(String turn) {
        this.turn = turn;
    }

    public String getTurn() {
        return turn;
    }
    @Override
    public String toString() {
        return new String(turn);
    }
}
