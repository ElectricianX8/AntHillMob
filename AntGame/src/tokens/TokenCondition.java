/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokens;

/**
 *
 * Token representing a condition
 */
public class TokenCondition extends Token {
    String condition;
    
    /**
     * Constructor
     * @param cond The condition
     */
    public TokenCondition(String cond) {
        this.condition = cond.trim();
    }

    /**
     * Returns the condition of the token.
     * @return The condition of the token.
     */
    public String getCondition() {
        return condition;
    }
    
    @Override
    public String toString() {
        return new String(condition);
    }
}
