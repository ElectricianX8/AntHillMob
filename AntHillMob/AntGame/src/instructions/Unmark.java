package instructions;

/**
 * Instruction to remove a marker from a cell.
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
     * Constructor.
     * @param markToRemove marker number to remove
     * @param stateToGoTo State to transition to once instruction has been executed.
     */
    public Unmark(int markToRemove, int stateToGoTo){
        this.markToRemove = markToRemove;
        this.stateToGoTo = stateToGoTo;
    }

    /**
     * Gets the Marker number to be removed.
     * @return The number of the marker to be removed.
     */
    public int getMarkToRemove() {
        return markToRemove;
    }

    /**
     * Set the number of the marker to be removed.
     * @param markToRemove the number of the marker to be removed.
     */
    public void setMarkToRemove(int markToRemove) {
        this.markToRemove = markToRemove;
    }

    /**
     * Get the state to transition to once the instruction has been executed.
     * @return the next instruction state to transition to.
     */
    public int getStateToGoTo() {
        return stateToGoTo;
    }

    /**
     * Set the next state to transition to.
     * @param stateToGoTo The next state to transition to post execution of instruction.
     */
    public void setStateToGoTo(int stateToGoTo) {
        this.stateToGoTo = stateToGoTo;
    }
    
    /**
     * Returns a String representation of the instruction.
     * @return Instruction as String.
     */
    @Override
    public String toString() {
        return "Unmark "+this.markToRemove+" "+this.stateToGoTo;
    } 
}
