/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

/**
 *
 * @author oliverthomas
 */
public class LexerException extends Exception {

    String error;
    
    public LexerException(String e) {
        this.error = e;
    }
    
}
