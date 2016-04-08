/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

/**
 * Represents an (x,y) coordinate.
 * [][] where first [] is the row, second [] the column
 */
public class Coordinate {
    private int x;
    private int y;
    
    public Coordinate(int x, int y){
        this.x = x;
        this.y = y;
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
