package instructions;

/**
 * Conditions that can be *sensed* by Sense class. Comprises of ConditionType
 * Enum, plus mark number that is used by Marker condition.
 */
public class Condition {

    private ConditionType condition;
    private int markNumber;

    /**
     * Constructor used for most conditions
     *
     * @param condition Must be of ConditionType
     */
    public Condition(ConditionType condition) {
        this.condition = condition;
        markNumber = 0;
    }

    /**
     * Constructor used with Marker condition
     *
     * @param condition Must be of ConditionType
     * @param mark Marker that we're sensing for
     */
    public Condition(ConditionType condition, int mark) throws NotValidInstructionException {
        if (condition == ConditionType.Marker) {
            this.condition = condition;
            markNumber = mark;
        } else {
            throw new NotValidInstructionException("Only Marker Instructions accept a parameter");
        }
    }

    /**
     * Gets the ConditionType to be *sensed* for
     *
     * @return ConditionType
     */
    public ConditionType getCondition() {
        return condition;
    }

    /**
     * Gets marker number to sense for
     *
     * @return marker to sense for
     */
    public int getMark() {
        return markNumber;
    }

    /**
     * Gets a String Representation of the class
     *
     * @return STring of the class
     */
    @Override
    public String toString() {
        if (this.condition == ConditionType.Marker) {
            return this.condition.toString() + " " + this.markNumber;
        } else {
            return this.condition.toString();
        }
    }
}
