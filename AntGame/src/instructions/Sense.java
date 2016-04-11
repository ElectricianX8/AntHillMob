package instructions;

/**
 * Sense Instruction class.
 * 
 */
public class Sense extends Instruction {
    private SenseDirection senseDirection;
    private int trueState;
    private int falseState;
    private Condition cond;
    
    /**
     * Default constructor for testing.
     * To be removed before final submission.
     */
    public Sense(){
        senseDirection = SenseDirection.HERE;
        trueState = 0;
        falseState = 0;
        cond = null;
    }
    
    /**
     * Constructor for Sense class. Takes direction to sense, states to transition
     * to for true or false conditions, and a condition to sense for.
     * @param senseDirection location to search at.
     * @param trueState State to go to if condition returns true
     * @param falseState State to go to if false
     * @param cond Condition to *sense* for
     */
    public Sense(SenseDirection senseDirection, int trueState, int falseState, Condition cond){
        this.senseDirection = senseDirection;
        this.trueState = trueState;
        this.falseState = falseState;
        this.cond = cond;
    }

    /**
     * Gets the location to sense in.
     * @return the senseDirection
     */
    public SenseDirection getSenseDirection() {
        return senseDirection;
    }

    /**
     * Sets the direction to sense in.
     * @param senseDirection the senseDirection to set
     */
    public void setSenseDirection(SenseDirection senseDirection) {
        this.senseDirection = senseDirection;
    }

    /**
     * Get the state to go to if true.
     * @return state to go to if true
     */
    public int getTrueState() {
        return trueState;
    }

    /**
     * Set the state to go to if true.
     * @param trueState the state to go to if true
     */
    public void setTrueState(int trueState) {
        this.trueState = trueState;
    }

    /**
     * Get the state to go to if false.
     * @return the state to go to if false.
     */
    public int getFalseState() {
        return falseState;
    }

    /**
     * Set the state to go to if false.
     * @param falseState the State to go to if false.
     */
    public void setFalseState(int falseState) {
        this.falseState = falseState;
    }

    /**
     * Get the condition to check for.
     * @return the condition this instruction checks for.
     */
    public Condition getCond() {
        return cond;
    }

    /**
     * Set the condition to check for.
     * @param cond the condition to check for
     */
    public void setCond(Condition cond) {
        this.cond = cond;
    }
    
    /**
     * Returns this object as a string.
     * @return 
     */
    @Override
    public String toString() {
        return "Sense "+this.senseDirection+" "+this.trueState+" "+
                this.falseState+" "+this.cond;
    }
}
