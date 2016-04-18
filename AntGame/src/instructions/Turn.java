package instructions;

/**
 * Instruction to turn the calling ant in a direction.
 */
public class Turn extends Instruction{
    private TurnDirection turnDirection;
    private int stateToGoTo;
    
    /**
     * Constructor for testing purposes.
     * To be removed before submission.
     */
    public Turn(){
        turnDirection = null;
        stateToGoTo = 0;
    }
    
    /**
     * Constructor for the Turn instruction.
     * @param td TurnDirection object representing the direction for the ant 
     * to turn in.
     * @param s the state to s=transition to post execution.
     */
    public Turn(TurnDirection td, int s){
        turnDirection = td;
        stateToGoTo = s;
    }

    /**
     * Get the TurnDirection for the ant to turn in.
     * @return direction for the ant to turn in.
     */
    public TurnDirection getTurnDirection() {
        return turnDirection;
    }

    /**
     * Set the TurnDirection for the ant to turn in.
     * @param turnDirection the direction for the ant to turn in.
     */
    public void setTurnDirection(TurnDirection turnDirection) {
        this.turnDirection = turnDirection;
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
     * String representation of the instruction.
     * @return String representation of the instruction.
     */
    @Override
    public String toString() {
        return "Turn "+this.turnDirection+" "+this.stateToGoTo;
    }
}
