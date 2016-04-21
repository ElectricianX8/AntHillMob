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
public class TokenName extends Token{
    String name;
    
    public TokenName(String s) {
        this.name = s.trim();
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return name;
    }
}
