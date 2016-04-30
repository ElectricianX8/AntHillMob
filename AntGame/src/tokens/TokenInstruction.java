/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokens;

/**
 *
 * Token representing an instruction
 */
public class TokenInstruction extends Token {
    String instruction;
    
    /**
     * Constructor
     * @param instruction The instruction
     */
    public TokenInstruction(String instruction) {
        this.instruction = instruction.trim();
    }

    /**
     * Returns the instruction of the token.
     * @return The instruction of the token.
     */
    public String getInstruction() {
        return instruction;
    }
    @Override
    public String toString() {
        return new String(instruction);
    }
}
