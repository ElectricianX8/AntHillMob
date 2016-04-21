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
public class TokenInstruction extends Token {
    String instruction;
    
    public TokenInstruction(String instruction) {
        this.instruction = instruction.trim();
    }

    public String getInstruction() {
        return instruction;
    }
    @Override
    public String toString() {
        return new String(instruction);
    }
}
