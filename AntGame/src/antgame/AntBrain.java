package antgame;

import instructions.Instruction;

/**
 * Class representing an ant's brain. 
 * Consists of an an array of Instruction objects.
 */
class AntBrain {
    
    private Instruction[] antbrain;
    
    /**
     * Constructor.
     * receives output from the AntBrainParser as input.
     * @param ab Array of instructions which constitutes the antbrain.
     */
    public AntBrain(Instruction[] ab) {
        antbrain = ab;
    }
    
    /**
     * Gets the Instruction for the state.
     * @param state Current state of Ant.
     * @return Instruction to be carried out.
     */
    public Instruction getInstructionAt(int state) {
        return antbrain[state];
    }
}
