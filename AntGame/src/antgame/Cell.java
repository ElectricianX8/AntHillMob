package antgame;

import java.util.ArrayList;

/**
 * Class to represent a cell on the gameboard.
 */
public class Cell {

    private Coordinate coordinate;
    private Terrain terrain;
    private int food;
    private int occupied;
    private Colour antColour;
    private Ant ant;                        // NOT SURE WHAT THIS IS FOR?
    private ArrayList<Integer> redMarkers;
    private ArrayList<Integer> blackMarkers;
    private Colour antHill;

    /** 
     * Constructor for testing purposes.
     * Should be removed before submisison.
     * @param terrain
     * @param antHill 
     */
    public Cell(Terrain terrain, Colour antHill) {

        redMarkers = new ArrayList<Integer>();
        blackMarkers = new ArrayList<Integer>();

        this.terrain = terrain;
        food = 0; // constructor parameter?
        ant = null;
        this.antHill = antHill; // constructor parameter?
        
        antColour = null;

    }
    
    /**
     * Constructor for the Cell class where Cell is an anthill.
     * @param x x coordinate fo the cell.
     * @param y y coordinate of the cell.
     * @param t terrain tyoe of the cell
     * @param food Num of food items located at this cell.
     * @param occupied ID of the ant at the cell, else -1.
     * @param anthill If the cell is an anthill, what colour tyoe is it
     */
    public Cell(int x, int y, Terrain t, int food, int occupied, Colour anthill) {
        this.coordinate = new Coordinate(x, y);
        this.terrain = t;
        this.food = food;
        this.occupied = occupied;
        this.antHill = anthill;
        this.redMarkers = new ArrayList<>(6);
        this.blackMarkers = new ArrayList<>(6);
        antColour = null;
    }
    
    /**
     * Constructor for Cells that are not anthills
     * @param x x coordinate of cell.
     * @param y y coordinate of cell.
     * @param t terrain type of the cell.
     * @param food number of food items at the cell.
     */
    public Cell(int x, int y, Terrain t, int food) {
        this.coordinate = new Coordinate(x, y);
        this.terrain = t;
        this.food = food;
        this.occupied = -1;
        this.redMarkers = new ArrayList<>(6);
        this.blackMarkers = new ArrayList<>(6);
    }
    
    /**
     * Get the terrain type of the cell.
     * @return Terrain of the cell.
     */
    public Terrain getTerrain() {
        return terrain;
    }

    /**
     * Get the number of food items in the cell.
     * @return The number of food items in the cell.
     */
    public int getFoodCount() {
        return food;
    }

    /**
     * Set the food count for the cell.
     * @param count new value of food items in the cell.
     */
    public void setFoodCount(int count) {
        food = count;
    }
    
    /**
     * Increment Food item count for this cell.
     */
    public void incrementFoodInCell(){
        food++;
    }
    
    /**
     * Decrement Food item count for this cell.
     */
    public void decrementFoodInCell(){
        food--;
    }

    /**
     * Boolean whether Cell is occupied by an ant or not.
     * @return True if ant is located at this cell, else false.
     */
    public boolean isOccupied() {
        return occupied > -1;
    }
    
    /**
     * Return the id num of the occupying ant.
     * @return id of ant at this cell. -1 if empty.
     */
    public int getOccupier(){
        return occupied;
    }

    /**
     * Not sure about this tbh.
     * @param ant 
     */
    public void setOccupied(Ant ant) {
        this.ant = ant;
    }
    
    /**
     * Set the cell to be occupied.
     * @param id id num of the ant occupying the cell.
     */
    public void setOccupied(int id){
        occupied = id;
    }
    
    /**
     * Ant has left the cell.
     * Set occupied to empty (-1)
     */
    public void removeOccupation(){
        occupied = -1;
    }
    
    /**
     * Not sure about this tbh.
     */
    public void clearAnt(){
        ant = null;
    }
    
    /**
     * Not sure about this tbh.
     * @return 
     */
    public Ant getAnt(){
        return ant;
    }

    /**
     * Add Marker to Cell.
     * @param c Colour of the ants Marking the cell.
     * @param marker Marker number to put at cell.
     * @throws Exception Incase Colour is not of the Colour Type.
     */
    public void addMarker(Colour c, int marker) throws Exception{
        Integer i = marker;
        
        if (c.equals(Colour.RED)) {
            if (!redMarkers.contains(i)) {
                redMarkers.add(i);
            }
        } else if (c.equals(Colour.BLACK)) {
            if (!blackMarkers.contains(i)) {
                blackMarkers.add(i);
            }
        } else {
            throw new Exception("Colour neither red nor black?");
        }
    }
    
    /**
     * Remove a Marker from a cell for a Colour.
     * @param c Colour of the team to remove marker for.
     * @param marker num of the marker to remove.
     * @throws Exception  In case Colour is somehow not of the colour type.
     */
    public void removeMarker(Colour c, int marker) throws Exception{
        Integer i = marker;
        
        if (c.equals(Colour.RED)) {
            if (redMarkers.contains(i)) {
                redMarkers.remove(i);
            }
        } else if (c.equals(Colour.BLACK)) {
            if (blackMarkers.contains(i)) {
                blackMarkers.remove(i);
            }
        } else {
            throw new Exception("Colour neither red nor black?");
        }
    }
    
    /**
     * Not sure about this tbh.
     * @param colour
     * @param marker 
     */
    public void setMarker(Colour colour, int marker) {

        ArrayList<Integer> markers = getMarkerSet(colour);
        if (!markers.contains(marker)){ //if it doesnt already exist
            markers.add(marker);
        }  
    }
    
    /**
     * Not sure about this tbh.
     * @param colour
     * @param marker 
     */
    public void clearMarker(Colour colour, int marker) {

        Integer mark = marker; // wrap in integer to prevent index removal
        ArrayList<Integer> markers = getMarkerSet(colour);
        if (markers.contains(marker)){ //check if exists
            markers.remove(mark);
        }  
    }
    
    /**
     * Does the cell Contain a colour teams marker?
     * @param c Colour team to check marker for
     * @param marker Marker to check the cell is marked for
     * @return True if cell contains the team's marker, else false.
     * @throws Exception Incase we look for a team colour that we're not expecting.
     */
    public boolean containsMarker(Colour c, int marker) throws Exception{
        
        Integer i = marker;
        
        if (c.equals(Colour.RED)) {
            return redMarkers.contains(i);
        } else if (c.equals(Colour.BLACK)) {
            return blackMarkers.contains(i);
        } else {
            throw new Exception("Colour neither red nor black?");
        }
        
    }
    
    /**
     * Method for testing. Should be removed before submission.
     * I think.
     * @param colour
     * @return 
     */
    public boolean findAnyMarker(Colour colour){
        ArrayList<Integer> markers = getMarkerSet(colour);
        
        return markers.size()>0;
    }

    /**
     * Internal method. Used in methods above.
     * @param colour
     * @return 
     */
    private ArrayList<Integer> getMarkerSet(Colour colour) {

        if (colour == Colour.RED) {
            return redMarkers;
        } else {
            return blackMarkers;
        }
    }
    
    /**
     * Returns if the Cell is an anthill or not.
     * @return True if cell is an anthill, else false.
     */
    public boolean isAntHill() {
        return antHill != null;
    }
    
    /**
     * Return true if the cell is a team's anthill.
     * @param colour Team to check if anthill for.
     * @return True if correct team's anthill, else false.
     */
    public boolean isAnthillFor(Colour colour){
        return antHill == colour;
    }

    /**
     * Return the coordinate of the cell.
     * @return Cell's Coordinate.
     */
    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    /**
     * Gets if this cell contains food.
     * @return True if Food items > 0, else false.
     */
    public boolean containsFood() {
        return this.food>0;
    }
    
    public void setAntColour(Colour colour){
        antColour = colour;
    }
    
    public Colour getAntColour(){
        return antColour;
    }
    
    public void removeAntColour(){
        antColour = null;
    }
    

}
