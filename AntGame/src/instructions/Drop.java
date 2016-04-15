package instructions;

/**
 * Instruction to drop a food item in the current cell if the ant 
 * is currently holding a food item.
 */
public class Drop extends Instruction {
    private int stateToGoTo;
  
    /**
     * Constructor for testing purposes.
     * To be removed before submission.
     */
    public Drop(){
        stateToGoTo = 0;
    }
    
    /**
     * Constructor for the instruction.
     * @param s State to transition to post execution.
     */
    public Drop(int s){
        stateToGoTo = s;
    }

    /**
     * Get the state to transition to post execution.
     * @return the state to transition to post execution.
     */
    public int getStateToGoTo() {
        return stateToGoTo;
    }

    /**
     * Set the state to transition to post execution.
     * @param stateToGoTo the state to transition to post execution.
     */
    public void setStateToGoTo(int stateToGoTo) {
        this.stateToGoTo = stateToGoTo;
    }
    
    /**
     * Get String representation of the instruction.
     * @return String representation of the instruction.
     */
    @Override
    public String toString() {
        return "Drop "+this.stateToGoTo;
    }
}
