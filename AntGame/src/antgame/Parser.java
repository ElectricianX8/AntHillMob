/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

import instructions.Instruction;
import java.util.ArrayList;

/**
 *
 * @author oliverthomas
 */
class Parser {

    public Parser() {}
    
    public Instruction[] parseAntBrain(ArrayList<Instruction> lexAntBrain) {
        Instruction[] parsedInstructionsToReturn;
        ArrayList<Instruction> parsedInstructions = new ArrayList<>();
        
        // TO DO
        parsedInstructionsToReturn = parsedInstructions.toArray(new Instruction[parsedInstructions.size()]);

        return parsedInstructionsToReturn;
    }
    
}
