/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package parsers;

// Exception for invalid tokens in world parsing.
public class InvalidMapTokenException extends Exception {
   public InvalidMapTokenException(String msg){
      super(msg);
   }
   
   
}