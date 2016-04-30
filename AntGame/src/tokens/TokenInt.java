/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokens;

/**
 *
 * Token representing an integer
 */
public class TokenInt extends Token {
    int num;
    
    /**
     * Constructor for integers
     * @param i Integer.
     */
    public TokenInt(int i) {
        this.num = i;
    }
    
    /**
     * Constructor for strings
     * @param i String representing an integer
     */
    public TokenInt(String i){
        String stringToInt = i.trim();
        Integer j = new Integer(stringToInt);
        this.num = j;
    }
    
    /**
     * Returns the integer of the token.
     * @return The integer of the token.
     */
    public int getNum() {
        return num;
    }
    @Override
    public String toString() {
        return new String(""+num);
    }
}
