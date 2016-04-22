package antgame;

import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * Cell class
 */
public class Cell {

    Terrain terrain;
    int foodCount;
    Ant ant;
    ArrayList<Integer> redMarkers;
    ArrayList<Integer> blackMarkers;
    Colour antHill;

    public Cell(Terrain terrain, Colour antHill) {

        redMarkers = new ArrayList<Integer>();
        blackMarkers = new ArrayList<Integer>();

        this.terrain = terrain;
        foodCount = 0; // constructor parameter?
        ant = null;
        this.antHill = antHill; // constructor parameter?

    }

    public Terrain getTerrain() {
        return terrain;
    }

    public int getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(int count) {
        foodCount = count;
    }

    public boolean isOccupied() {
        return ant != null;

    }

    public void setOccupied(Ant ant) {
        this.ant = ant;
    }
    
    public void clearAnt(){
        ant = null;
    }
    
    public Ant getAnt(){
        return ant;
    }

    public void setMarker(Colour colour, int marker) {

        ArrayList<Integer> markers = getMarkerSet(colour);
        if (!markers.contains(marker)){ //if it doesnt already exist
            markers.add(marker);
        }  
    }

    public void clearMarker(Colour colour, int marker) {

        Integer mark = new Integer(marker); // wrap in integer to prevent index removal
        ArrayList<Integer> markers = getMarkerSet(colour);
        if (markers.contains(marker)){ //check if exists
            markers.remove(mark);
        }  
    }
    
    public boolean findMarker(Colour colour, int marker){
        
        ArrayList<Integer> markers = getMarkerSet(colour);
       
        return markers.contains(marker);
        
    }
    
    public boolean findAnyMarker(Colour colour){
        ArrayList<Integer> markers = getMarkerSet(colour);
        
        return markers.size()>0;
    }

    private ArrayList<Integer> getMarkerSet(Colour colour) {

        if (colour == Colour.RED) {
            return redMarkers;
        } else {
            return blackMarkers;
        }
    }
    
    
    public boolean isAnthill(Colour colour){
        
        return antHill == colour;
    }
    
    
    public Color getAntColour(){
        if(ant==null){
            return new Color(250,250,210);
        }
        else{
            if(ant.getColour()==Colour.RED){
                return Color.RED;
            }
            else{
                return Color.BLACK;
            }
        }
    }
    
    
    public Colour getAntHillColour(){
        
        if(antHill == null){
            return null;
        }
        else {
            return antHill;
        }
        
    }

}
