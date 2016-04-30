/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;

/**
 *
 * @author oliverthomas
 */
public class ParsingException extends Exception {
    String error;
    public ParsingException(String error) {
        this.error = error;
    }
    
}
