package antgame;

/**
 * Represents a (y, x) coordinate.
 */
public class Coordinate {
    private int x;
    private int y;
    
    /**
     * Constructor.
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Coordinate(int y, int x){
       this.y = y;
        this.x = x;

    }

    /**
     * Returns the x coordinate.
     * @return x coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Sets the x coordinate.
     * @param x The x coordinate to set.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Returns the y coordinate.
     * @return y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the y coordinate.
     * @param y The y coordinate to set.
     */
    public void setY(int y) {
        this.y = y;
    }
}
