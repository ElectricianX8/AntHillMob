/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokens;

/**
 *
 * Token representing a direction
 */
public class TokenDirection extends Token {
    String direction;
    
    /**
     * Constructor.
     * @param dir The direction.
     */
    public TokenDirection(String dir) {
        this.direction = dir.trim();
    }

    /**
     * Returns the direction the token holds.
     * @return The Direction.
     */
    public String getDirection() {
        return direction;
    }
    
    @Override
    public String toString() {
        return new String(direction);
    }
}
