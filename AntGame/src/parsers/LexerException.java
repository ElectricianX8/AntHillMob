/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

/**
 *
 * Exception for invalid brains in the lexing process.
 */
public class LexerException extends Exception {

    String error;
    
    /**
     * Constructor for the exception.
     * @param e Message of the exception.
     */
    public LexerException(String e) {
        this.error = e;
    }
    
}
