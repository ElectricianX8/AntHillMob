/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package antgame;

import java.util.ArrayList;

/**
 *
 * Game GameBoard
 */
public class GameBoard {
    
    
    Cell [][] board;
    ArrayList<Ant> ants;
    
    //test only, phase out later on
    public GameBoard(int height, int width, ArrayList<Ant> ants){
    
        board = new Cell[height][width];
        board[0][0] = new Cell(Terrain.ROCKY, null);
        board[0][1] = new Cell(Terrain.ROCKY, null);
        board[1][1] = new Cell(Terrain.ROCKY, null);
        this.ants = ants;
        //fill up cells with ants here or do outside? 
    }
    
    //use this in world parser
    public GameBoard(Cell[][] board, ArrayList<Ant> ants){
        this.board = board;
        this.ants = ants;
    }
    
    
    public Cell getCellAtPosition(Coordinate position){
        
        return board[position.getX()][position.getY()];
        
    }
    
    
    
    //True if position is rocky, false if clear.
    public boolean rocky(Coordinate position){
        
        return getCellAtPosition(position).getTerrain() == Terrain.ROCKY;
    }
    
    
    public boolean some_ant_is_at(Coordinate position){
        
        return getCellAtPosition(position).isOccupied();
    }
    
    
    public Ant ant_at(Coordinate position){
        
        return getCellAtPosition(position).getAnt();
    }
    
    //assumes ant is already in
    public void set_ant_at(Coordinate position, Ant ant){
        
        //maybe it shouldnt assume that?
        getCellAtPosition(position).setOccupied(ant);
    }
    
    //assumes ant is already in and only moving, not dying
    public void clear_ant_at(Coordinate position){
        //reset ants position?
        getCellAtPosition(position).clearAnt();
    }
    
    public boolean ant_is_alive(int id){
        return getAnt(id)!= null;
    }
    
    public Coordinate find_ant(int id){
       // not null check?
        return getAnt(id).getCurrentPosition();
    }
    
   
    public void kill_ant_at(Coordinate position){
        
        Cell cell = getCellAtPosition(position);
        // check if cell has ant or not?
        Ant ant = cell.getAnt();
        ants.remove(ant);
        ant.setCurrentPosition(null);
        clear_ant_at(position);
        
    }
    
    
    //null if no ant
    private Ant getAnt(int id){
   
        for(Ant ant: ants){
            if(ant.getId() == id){
                return ant;
            }
        }
        return null;
        
    }
    
    
    
    
    public int food_at(Coordinate position){
        return getCellAtPosition(position).getFoodCount();
    }
    
    public void set_food_at(Coordinate position, int foodCount){
        getCellAtPosition(position).setFoodCount(foodCount);
    }
    
    
    public void set_marker_at(Coordinate position, Colour colour, int marker){
        
        getCellAtPosition(position).setMarker(colour, marker);
    }
    
    
    public void clear_marker_at(Coordinate position, Colour colour, int marker){
        
        getCellAtPosition(position).clearMarker(colour, marker);
    }
    
    public boolean check_marker_at(Coordinate position, Colour colour, int marker){
        
        return getCellAtPosition(position).findMarker(colour, marker);
    }
    
    public boolean check_any_marker_at(Coordinate position, Colour colour){
        
       
        return getCellAtPosition(position).findAnyMarker(colour);
    }
    
    
    public boolean anthill_at(Coordinate position, Colour colour){
        
        return getCellAtPosition(position).isAnthill(colour);
        
    }
    
    
    public void printBoard(){
        
        for(int height = 0; height < board.length; height++){
            for(int width = 0; width < board[height].length; width++){
                
                Coordinate position = new Coordinate(height, width);
                
                if(rocky(position)){
                    System.out.print("#");
                }
                else if(food_at(position)!=0){
                    System.out.print(food_at(position));
                }
                // do these checks to see if ants are correctly placed on top of anthill during parsing
                else if(anthill_at(position, Colour.RED) && some_ant_is_at(position) && ant_at(position).getColour() == Colour.RED){
                    System.out.print("+");
                }
                else if(anthill_at(position, Colour.BLACK) && some_ant_is_at(position) && ant_at(position).getColour() == Colour.BLACK){
                    System.out.print("-");
                }
                else{
                    System.out.print(".");
                }
            }
            System.out.println("");
        }
        
    }
    
 

}
