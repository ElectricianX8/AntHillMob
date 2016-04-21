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
public class TokenCondition extends Token {
    String condition;
    
    public TokenCondition(String cond) {
        this.condition = cond.trim();
    }

    public String getCondition() {
        return condition;
    }
    @Override
    public String toString() {
        return new String(condition);
    }
}
