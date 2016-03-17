package antgame;

/**
 *
 * @author alisaleem
 */
public class Ant {

    private int id;
    private Colour colour;
    private int state;
    private int resting;
    private int direction;
    private boolean hasFood;
    private Coordinate currentPosition;
    private boolean isAlive;

    public Ant(int id, Colour colour, int direction, int x, int y) {
        this.id = id;
        this.colour = colour;
        state = 0;
        resting = 0;
        this.direction = direction;
        hasFood = false;
        currentPosition = new Coordinate(x, y);
        isAlive = true;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the colour
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * @param colour the colour to set
     */
    public void setColour(Colour colour) {
        this.colour = colour;
    }

    /**
     * @return the state
     */
    public int getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * @return the resting
     */
    public int getResting() {
        return resting;
    }

    /**
     * @param resting the resting to set
     */
    public void setResting(int resting) {
        this.resting = resting;
    }

    /**
     * Decrease the resting counter.
     */
    public void decResting() {
        resting--;
    }

    /**
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * @return the hasFood
     */
    public boolean getHasFood() {
        return hasFood;
    }

    /**
     * @param hasFood the hasFood to set
     */
    public void setHasFood(boolean hasFood) {
        this.hasFood = hasFood;
    }

    /**
     * @return the currentPosition
     */
    public Coordinate getCurrentPosition() {
        return currentPosition;
    }

    /**
     * @return the isAlive
     */
    public boolean getIsAlive() {
        return isAlive;
    }

    /**
     * @param isAlive the isAlive to set
     */
    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    
    public int numAdjacentAnts(){
        throw new UnsupportedOperationException("Method not yet defined");
    }

}
