package instructions;

/**
 * Instruction for the calling ant to move forward one cell from the cell
 * it is currently in to the one that it is facing.
 */
public class Move {

    private int stateToGoToIfClear;
    private int StateToGoToIfBlocked;

    /**
     * Constructor for testing purposes.
     * To be removed before submission.
     */
    public Move() {
        stateToGoToIfClear = 0;
        StateToGoToIfBlocked = 0;
    }

    /**
     * Constructor for the instruction.
     * @param stateToGoToIfClear State to transition to if move is succesful.
     * @param StateToGoToIfBlocked State to go to if move is not successful.
     */
    public Move(int stateToGoToIfClear, int StateToGoToIfBlocked) {
        this.stateToGoToIfClear = stateToGoToIfClear;
        this.StateToGoToIfBlocked = StateToGoToIfBlocked;
    }

    /**
     * Get state to transition to if able to complete move.
     * @return the state to transition to if move is able to be completed.
     */
    public int getStateToGoToIfClear() {
        return stateToGoToIfClear;
    }

    /**
     * Set state to transition to if able to move.
     * @param stateToGoToIfClear the state state to transition to if 
     * able to move.
     */
    public void setStateToGoToIfClear(int stateToGoToIfClear) {
        this.stateToGoToIfClear = stateToGoToIfClear;
    }

    /**
     * Get the state to transition to if unable to move to next cell.
     * @return the State to transition to if unable to move.
     */
    public int getStateToGoToIfBlocked() {
        return StateToGoToIfBlocked;
    }

    /**
     * Set the state to transition to if unable to move.
     * @param StateToGoToIfBlocked the State to transition to if unable to move.
     */
    public void setStateToGoToIfBlocked(int StateToGoToIfBlocked) {
        this.StateToGoToIfBlocked = StateToGoToIfBlocked;
    }
    
    /**
     * String representation of the instruction.
     * @return String representation of the instruction.
     */
    @Override
    public String toString() {
        return "Move "+this.stateToGoToIfClear+" "+this.StateToGoToIfBlocked;
    }
}
