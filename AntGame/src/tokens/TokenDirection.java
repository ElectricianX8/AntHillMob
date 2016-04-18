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
public class TokenDirection extends Token {
    String direction;
    
    public TokenDirection(String dir) {
        this.direction = dir;
    }

    public String getDirection() {
        return direction;
    }
    @Override
    public String toString() {
        return new String(direction);
    }
}
