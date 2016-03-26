package antgame;

/**
 * Ant class
 */
public class Ant {

    private static int idCount = 0;
    
    private final int id;
    private final Colour colour;
    private int state;
    private int resting;
    private int direction;
    private boolean hasFood;
    private Coordinate currentPosition;
    private boolean isAlive;

    public Ant(Colour colour, int direction, int x, int y) {
        
        this.colour = colour;
        this.direction = direction;
        id = ++idCount;
        state = 0;
        resting = 0;
        hasFood = false;
        isAlive = true;
        currentPosition = new Coordinate(x, y);
    }

    /**
     * @return Returns ID number of the ant.
     */
    public int getId() {
        return id;
    }
    
    /**
     * @return Returns the colour of the ant.
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * @return Returns the state of the ant.
     */
    public int getState() {
        return state;
    }

    /**
     * Sets the state of the ant.
     * @param state The state to set
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * @return Returns the resting count of an ant
     */
    public int getResting() {
        return resting;
    }

    /**
     * Sets the resting count of an ant
     * @param resting The amount of time to rest before next move
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
     * @return Returns the direction of the ant.
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Sets the direction of the ant
     * @param direction The direction to set.
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

    /**
     * @return Returns whether the ant currently holds food.
     */
    public boolean getHasFood() {
        return hasFood;
    }

    /**
     * Sets whether the ant currently holds fold or not.
     * @param hasFood True if ant has food, false otherwise.
     */
    public void setHasFood(boolean hasFood) {
        this.hasFood = hasFood;
    }

    /**
     * @return Returns the current position of the ant.
     */
    public Coordinate getCurrentPosition() {
        return currentPosition;
    }

    /**
     *
     * @return Returns whether the ant is alive or not.
     */
    public boolean getIsAlive() {
        return isAlive;
    }

    /**
     * Sets whether the ant is alive or not.
     * @param isAlive True if alive, false otherwise
     */
    public void setIsAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
    
    /**
     * Sets the current position of the ant.
     * @param position Position of the ant.
     */
    public void setCurrentPosition(Coordinate position){
        currentPosition = position;
    }

}
