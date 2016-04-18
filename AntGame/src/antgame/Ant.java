package antgame;

/**
 * Ant class
 */
public class Ant {

    private static int idCount = 0;
    
    private final int id;
    private final Colour colour;
    private int state = 0;
    private int resting = 0;
    private int direction = 0;
    private boolean hasFood = false;
    private Coordinate currentPosition;
    private boolean isAlive = true;

    /**
     * Constructor for the Ant Class
     * @param c the Colour team of the Ant.
     */
    public Ant(Colour c){
        id = idCount++;
        colour = c;
    }
    
    
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
     * gets the ID num of the Ant.
     * @return Returns ID number of the ant.
     */
    public int getId() {
        return id;
    }
    
    /**
     * Return the COlour of the Ant.
     * @return Returns the colour of the ant.
     */
    public Colour getColour() {
        return colour;
    }

    /**
     * Return the Ant's current state.
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
     * Gets the number of turns the ant has to rest.
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
     * Get the ant's direction. 
     * 0-5, 0 at East, incrementing as you rotate clockwise.
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
     * Gets whether the Ant is currently holding food.
     * @return True if the Ant is currently holding Food, else false.
     */
    public boolean hasFood() {
        return hasFood;
    }

    /**
     * Sets whether the ant currently holds food or not.
     * @param hasFood True if ant has food, false otherwise.
     */
    public void setHasFood(boolean hasFood) {
        this.hasFood = hasFood;
    }

    /**
     * Gets the Coordinate of the Ant
     * @return Returns the current position of the ant.
     */
    public Coordinate getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Returns whether the ant is alive or not
     * @return True if the Ant is alive, else false.
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

    /**
     * Gets the colour of the ant's opposition
     * @return Colour of opposition ants
     */
    public Colour getFoeColour() {
        if (this.colour.equals(Colour.BLACK)) {
            return Colour.RED;
        } else {
            return Colour.BLACK;
        }
    }

}
