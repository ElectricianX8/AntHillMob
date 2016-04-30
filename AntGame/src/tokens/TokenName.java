/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokens;

/**
 *
 * Token representing a name
 */
public class TokenName extends Token{
    String name;
    
    /**
     * Constructor
     * @param s Name
     */
    public TokenName(String s) {
        this.name = s.trim();
    }

    /**
     * Returns the name the token holds.
     * @return The name the token holds.
     */
    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return name;
    }
}
