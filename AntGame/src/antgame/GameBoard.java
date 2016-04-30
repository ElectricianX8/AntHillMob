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
    ArrayList<Coordinate> antHills;
    
    //test only, phase out later on
    public GameBoard(int height, int width){
    
        board = new Cell[height][width];
        board[0][0] = new Cell(Terrain.ROCKY, null);
        board[0][1] = new Cell(Terrain.ROCKY, null);
        board[1][1] = new Cell(Terrain.ROCKY, null);
        //fill up cells with ants here or do outside? 
    }
    
    //use this in world parser
    public GameBoard(Cell[][] board, ArrayList<Coordinate> antHills){
        this.board = board;
        this.antHills = antHills;
    }
    
    
    public Cell getCellAtPosition(Coordinate position){
        
        //boundary check here or outside??
        //add isvalid position method somewhere, cover for it in game class?
        
        return board[position.getY()][position.getX()];
        
    }
    
    
    
    
    
    //True if position is isRocky, false if clear.
    public boolean isRocky(Coordinate position){
        
        return getCellAtPosition(position).getTerrain() == Terrain.ROCKY;
    }
    
    
    public boolean isAntAt(Coordinate position){
        
        return getCellAtPosition(position).isOccupied();
    }
    
    
    public Ant antAt(Coordinate position){
        
        return getCellAtPosition(position).getAnt();
    }
    
    //assumes ant is already in
    public void setAntAt(Coordinate position, Ant ant){
        
        //maybe it shouldnt assume that?
        getCellAtPosition(position).setOccupied(ant);
    }
    
    //assumes ant is already in and only moving, not dying
    public void clearAntAt(Coordinate position){
        //reset ants position?
        getCellAtPosition(position).clearAnt();
    }
    

    public int getFoodAt(Coordinate position){
        return getCellAtPosition(position).getFoodCount();
    }
    
    public void setFoodAt(Coordinate position, int foodCount){
        getCellAtPosition(position).setFoodCount(foodCount);
    }
    
    
    public void setMarkerAt(Coordinate position, Colour colour, int marker){
        
        getCellAtPosition(position).setMarker(colour, marker);
    }
    
    
    public void clearMarkerAt(Coordinate position, Colour colour, int marker){
        
        getCellAtPosition(position).clearMarker(colour, marker);
    }
    
    public boolean checkMarkerAt(Coordinate position, Colour colour, int marker){
        
        return getCellAtPosition(position).findMarker(colour, marker);
    }
    
    public boolean checkAnyMarkerAt(Coordinate position, Colour colour){
        
       
        return getCellAtPosition(position).findAnyMarker(colour);
    }
    
    
    public boolean isAnthillAt(Coordinate position, Colour colour){
        
        return getCellAtPosition(position).isAnthill(colour);
        
    }
    
    
    public void printBoard(){
        
        for(int height = 0; height < board.length; height++){
            for(int width = 0; width < board[height].length; width++){
                
                Coordinate position = new Coordinate(height, width);
                
                if(isRocky(position)){
                    System.out.print("#");
                }
                else if(getFoodAt(position)!=0){
                    System.out.print(getFoodAt(position));
                }
                // do these checks to see if ants are correctly placed on top of anthill during parsing
                else if(isAnthillAt(position, Colour.RED) && isAntAt(position) && antAt(position).getColour() == Colour.RED){
                    System.out.print("+");
                }
                else if(isAnthillAt(position, Colour.BLACK) && isAntAt(position) && antAt(position).getColour() == Colour.BLACK){
                    System.out.print("-");
                }
                else{
                    System.out.print(".");
                }
            }
            System.out.println("");
        }
        
    }

    //throw null if out of bounds?
    public Coordinate getAdjacentCell(Coordinate position, int direction) {
        
        
        int x = position.getX();
        int y = position.getY();
        boolean isEven;
        
        //what does it mean for coordinates to be even? the same number for both? 

        isEven = y%2==0;         //check if even or not
        
        if(direction==0){
            x+=1;
        }
        else if(direction==1){
            if(isEven){
                y+=1;
            }else{
                x+=1;
                y+=1;
            }
        }
        else if(direction==2){
            if(isEven){
                x-=1;
                y+=1;
            }else{
                y+=1;
            }
        }
        else if(direction==3){
            x-=1;
        }
        else if(direction==4){
            if(isEven){
                x-=1;
                y-=1;
            }else{
                y-=1;
            }
        }
        else if(direction==5){
            if(isEven){
                y-=1;
            }else{
                x+=1;
                y-=1;
            }
        }
        
        if(x<0 || y<0 || x > board.length-1 || y > board[0].length-1){
            return null;
        }
        else{
            return new Coordinate(y, x);
        }
       
    }
    
    public Cell[][] getHexGrid(){
       return board; 
    }
    
    
    public ArrayList<Coordinate> getAnthills(){
        return antHills;
    }
    
    
    
 

}
