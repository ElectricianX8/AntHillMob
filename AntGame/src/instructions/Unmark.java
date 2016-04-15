package instructions;

/**
 * Instruction to remove a marker from the cell the calling ant is currently in.
 */
public class Unmark extends Instruction {
    private int markToRemove;
    private int stateToGoTo;
    
    /**
     * Constructor for testing.
     * Should be removed before submission.
     */
    public Unmark(){
        markToRemove = 0;
        stateToGoTo = 0;
    }
    
    /**
     * Constructor for the instruction.
     * @param markToRemove
     * @param stateToGoTo 
     */
    public Unmark(int markToRemove, int stateToGoTo){
        this.markToRemove = markToRemove;
        this.stateToGoTo = stateToGoTo;
    }

    /**
     * Get the marker to remove from the cell.
     * @return the marker to be removed.
     */
    public int getMarkToRemove() {
        return markToRemove;
    }

    /**
     * Set the marker to be removed from a cell.
     * @param markToRemove the marker to remove from a cell.
     */
    public void setMarkToRemove(int markToRemove) {
        this.markToRemove = markToRemove;
    }

    /**
     * Get the state to transition to once the instruction has been carried out.
     * @return the state to transition to post instruction
     */
    public int getStateToGoTo() {
        return stateToGoTo;
    }

    /**
     * Set the state to transition to once the instruction has been carried out.
     * @param stateToGoTo the state to transition to post instruction.
     */
    public void setStateToGoTo(int stateToGoTo) {
        this.stateToGoTo = stateToGoTo;
    }
    
    /**
     * Returns the instruction as a String
     * @return String representation of the instruction.
     */
    @Override
    public String toString() {
        return "Unmark "+this.markToRemove+" "+this.stateToGoTo;
    }
    
    
}
