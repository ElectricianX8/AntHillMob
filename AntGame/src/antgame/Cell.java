package antgame;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Class representing a cell entity.
 */
public class Cell {

    private Terrain terrain;
    private int foodCount;
    private Ant ant;
    ArrayList<Integer> redMarkers;
    ArrayList<Integer> blackMarkers;
    private Colour antHill;
    Colour antColour;

    /**
     * Constructor for cell objects.
     * @param terrain Type of terrain for the cell.
     * @param antHill If the cell is an anthill, pass in it's colour, otherwise null.
     */
    public Cell(Terrain terrain, Colour antHill) {

        redMarkers = new ArrayList<Integer>();
        blackMarkers = new ArrayList<Integer>();

        this.terrain = terrain;
        foodCount = 0;
        ant = null;
        this.antHill = antHill;

    }

    /**
     * Returns the type of terrain this cell represents.
     * @return The type of terrain of this cell.
     */
    public Terrain getTerrain() {
        return terrain;
    }

    /**
     * Returns the amount of food in this cell.
     * @return The amount of food in this cell.
     */
    public int getFoodCount() {
        return foodCount;
    }

    /**
     * Set the amount of food in this cell.
     * @param count Amount of food to be set in this cell.
     */
    public void setFoodCount(int count) {
        foodCount = count;
    }

    /**
     * Returns whether the cell is occupied by an ant or not.
     * @return True if occupied, false otherwise.
     */
    public boolean isOccupied() {
        return ant != null;

    }

    /**
     * Set an ant to occupy this cell.
     * @param ant An Ant to occupy this cell.
     */
    public void setOccupied(Ant ant) {
        this.ant = ant;
        antColour = ant.getColour();
    }
    
    /**
     * Remove the currently occupying ant from this cell.
     */
    public void clearAnt(){
        ant = null;
        antColour = null;
    }
    
    /**
     * Return the ant that current occupies this cell.
     * @return The ant occupying this cell.
     */
    public Ant getAnt(){
        return ant;
    }

    /**
     * Place the specified marker on this cell.
     * @param colour Colour of the ant placing the marker.
     * @param marker The marker to be placed.
     */
    public void setMarker(Colour colour, int marker) {

        ArrayList<Integer> markers = getMarkerSet(colour);
        if (!markers.contains(marker)){ //if it doesnt already exist
            markers.add(marker);
        }  
    }

    /**
     * Remove the specified marker from the cell.
     * @param colour Colour of the marker to remove
     * @param marker The marker to be removed.
     */
    public void clearMarker(Colour colour, int marker) {

        Integer mark = new Integer(marker); // wrap in integer to prevent index removal
        ArrayList<Integer> markers = getMarkerSet(colour);
        if (markers.contains(marker)){ //check if exists
            markers.remove(mark);
        }  
    }
    
    /**
     * Returns whether the specified marker exists in this cell.
     * @param colour Colour of the marker to find.
     * @param marker The marker to find.
     * @return True if the cell contains the marker, false otherwise.
     */
    public boolean findMarker(Colour colour, int marker){
        
        ArrayList<Integer> markers = getMarkerSet(colour);
       
        return markers.contains(marker);
    }
    
    /**
     * Returns whether this cell contains any markers of the specified colour.
     * @param colour Colour of the markers to find.
     * @return True if the cell contains the marker, false otherwise.
     */
    public boolean findAnyMarker(Colour colour){
        ArrayList<Integer> markers = getMarkerSet(colour);
        
        return markers.size()>0;
    }

    //Return the set of markers of the specified colour.
    private ArrayList<Integer> getMarkerSet(Colour colour) {

        if (colour == Colour.RED) {
            return redMarkers;
        } else {
            return blackMarkers;
        }
    }
    
    /**
     * Returns whether the cell is an anthill of the specified colour.
     * @param colour The colour of the anthill.
     * @return True if the cell is an anthill of the specified colour, false otherwise.
     */
    public boolean isAnthill(Colour colour){
        
        return antHill == colour;
    }
    
    /**
     * Return the colour of the ant currently residing within this cell.
     * @return If an ant resides within this cell, return its colour, otherwise return a clear cell colour.
     */
    public Color getAntColour(){
        //method to help cell renderer
        if(ant==null){
            return new Color(250,250,210);
        }
        else{
            if(antColour==Colour.RED){
                return Color.RED;
            }
            else{
                return Color.BLACK;
            }
        }
    }
    
    /**
     * Returns the colour of the anthill.
     * @return Return black/red if the cell is an anthill, else return null.
     */
    public Colour getAntHillColour(){
        
        if(antHill == null){
            return null;
        }
        else {
            return antHill;
        }
        
    }

}
