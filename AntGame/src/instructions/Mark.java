package instructions;

/**
 * Instruction to mark a cell.
 */
public class Mark extends Instruction {
    private int markToLeave;
    private int stateToGoTo;
    
    /**
     * Default constructor for testing.
     * Should be removed before submission.
     */
    public Mark(){
        markToLeave = 0;
        stateToGoTo = 0;
    }
    
    /**
     * Constructor. Takes a marker to leave and state to transition to.
     * @param markToLeave Marker to leave.
     * @param stateToGoTo State to go to.
     */
    public Mark(int markToLeave, int stateToGoTo){
        this.markToLeave = markToLeave;
        this.stateToGoTo = stateToGoTo;
    }

    /**
     * Get the marker to leave.
     * @return marker to leave.
     */
    public int getMarkToLeave() {
        return markToLeave;
    }

    /**
     * Set the marker to leave.
     * @param markToLeave the marker to leave
     */
    public void setMarkToLeave(int markToLeave) {
        this.markToLeave = markToLeave;
    }

    /**
     * Get the state to go to once a marker has been set.
     * @return the state to transition to.
     */
    public int getStateToGoTo() {
        return stateToGoTo;
    }

    /**
     * Set the state to go to next.
     * @param stateToGoTo the state to go to next
     */
    public void setStateToGoTo(int stateToGoTo) {
        this.stateToGoTo = stateToGoTo;
    }
    
    /**
     * Instruction as a string.
     * @return Instruction as a String
     */
    @Override
    public String toString() {
        return "Mark "+this.markToLeave+" "+this.stateToGoTo;
    }
}
