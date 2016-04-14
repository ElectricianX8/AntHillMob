/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instructions;

/**
 *
 * @author ollie
 */
class NotValidInstructionException extends Exception {
    String message;
    public NotValidInstructionException(String s) {
        this.message = s;
    }
    
}
