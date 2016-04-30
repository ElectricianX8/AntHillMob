/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

/**
 *
 * Exception for invalid brains in the parsing process.
 */
public class ParsingException extends Exception {
    String error;
    
    /**
     * Constructor for the exception.
     * @param error Message of the exception.
     */
    public ParsingException(String error) {
        this.error = error;
    }
    
}
