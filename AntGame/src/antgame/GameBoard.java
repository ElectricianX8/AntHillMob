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
        
        //boundary check here or outside??
        //add isvalid position method somewhere, cover for it in game class?
        
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
        //move this out to upper wrapper?
        ants.remove(ant);
        //maybe don't delete here, simply check death flag to true, and check in step
        ant.setCurrentPosition(null);
        ant.setIsAlive(false);
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

    //throw null if out of bounds?
    public Coordinate adjacent_cell(Coordinate position, int direction) {
        
        
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
            return new Coordinate(x, y);
        }
       
    }
    
    public Cell[][] getHexGrid(){
       return board; 
    }
    
    
    
 

}
