package instructions;

/**
 * Instruction to pick up a food item from the current cell and for the 
 * ant executing the instruction to hold it.
 */
public class Pickup extends Instruction {
    private int trueState;
    private int falseState;
    
    /**
     * Constructor for testing.
     * Should be removed before submission.
     */
    public Pickup(){
        trueState = 0;
        falseState = 0;
    }
    
    /**
     * Constructor for the instruction. 
     * @param trueState the state to transition to if able to pick up an item.
     * @param falseState the state to transition to if unable to pick 
     * up an item.
     */
    public Pickup(int trueState, int falseState){
        this.trueState = trueState;
        this.falseState = falseState;
    }

    /**
     * Gets the state to transition to if able to pick up an item.
     * @return the state to transition to if able to pick up an item.
     */
    public int getTrueState() {
        return trueState;
    }

    /**
     * Set the state to transition to if can pick up an item.
     * @param trueState the state to transition to if can pick up an item.
     */
    public void setTrueState(int trueState) {
        this.trueState = trueState;
    }

    /**
     * Get the state to transition to if unable to pick up an item.
     * @return the state to transition to if unable to pick up an item.
     */
    public int getFalseState() {
        return falseState;
    }

    /**
     * Set the state to transition to if unable to pick up an item.
     * @param falseState the state to transition to if unable to pick up an item.
     */
    public void setFalseState(int falseState) {
        this.falseState = falseState;
    }
    
    /**
     * Get a sString representation of the instruction.
     * @return The instruction as a String.
     */
    @Override
    public String toString() {
        return "Pickup "+this.trueState+" "+this.falseState;
    }
}
