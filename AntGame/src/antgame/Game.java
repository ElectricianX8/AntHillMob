/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

import instructions.Condition;
import instructions.ConditionType;




/**
 *
 * Top-level class for managing game/link with gui here?
 */
public class Game {

    GameBoard board;

    public Game(GameBoard board) {
        //pass gui in?
        //pass in filenames and read here, or read outside and pass in completed objects(*)?
        this.board = board;
    }
    
    
    public Colour other_color(Colour colour){
        
        if(colour == Colour.RED){
            return Colour.BLACK;
        }else{
            return Colour.RED;
        }
        
    }
    
    public int turn(TurnDirection turnDirection, int direction){
        
        if(turnDirection == TurnDirection.LEFT){
            return (direction+5) % 6;
        }
        else{
            return (direction+1) % 6;
        }
    }
    
    public Coordinate sensed_cell(Coordinate position, int direction, SenseDirection sense){
        
        if(sense==SenseDirection.HERE){
            return position;
        }
        else if(sense==SenseDirection.AHEAD){
            return board.adjacent_cell(position, direction);
        }
        else if(sense==SenseDirection.LEFTAHEAD){
            return board.adjacent_cell(position, turn(TurnDirection.LEFT, direction));
        }
        else if(sense==SenseDirection.RIGHTAHEAD){
            return board.adjacent_cell(position, turn(TurnDirection.RIGHT, direction));
        }else{
            return null;
        }
    }

    
    public boolean cell_matches(Coordinate position, Condition condition, Colour colour) {

        //convert this into switch statement to make it easier to read?
        if (board.rocky(position)) {
            return condition.getCondition() == ConditionType.ROCK;
        } else {
          if(condition.getCondition() == ConditionType.FRIEND){
              //check first for null, then colour?
              return board.some_ant_is_at(position) && board.ant_at(position).getColour() == colour;
          }
          else if(condition.getCondition() == ConditionType.FOE){
              return board.some_ant_is_at(position) && board.ant_at(position).getColour() != colour;
          }
          else if(condition.getCondition() == ConditionType.FRIENDWITHFOOD){
              return board.some_ant_is_at(position) && board.ant_at(position).getColour() == colour && board.ant_at(position).hasFood();
          }
          else if(condition.getCondition() == ConditionType.FOEWITHFOOD){
              return board.some_ant_is_at(position) && board.ant_at(position).getColour() != colour && board.ant_at(position).hasFood();
          }
          else if(condition.getCondition() == ConditionType.FOOD){
              return board.food_at(position) > 0;
          }
          else if(condition.getCondition() == ConditionType.ROCK){
              return false;
          }
          else if(condition.getCondition() == ConditionType.MARKER){
              return board.check_marker_at(position, colour, condition.getMark());
          }
          else if(condition.getCondition() == ConditionType.FOEMARKER){
              return board.check_any_marker_at(position, other_color(colour));
          }
          else if(condition.getCondition() == ConditionType.HOME){
              return board.anthill_at(position, colour);
          }
          else if(condition.getCondition() == ConditionType.FOEHOME){
              return board.anthill_at(position, other_color(colour));
          }
          else{
              return false;
          }
        }
    }
    
    
    public int adjacent_ants(Coordinate position, Colour colour){
        
        int antCount = 0;
        
        //check all directions
        for(int i=0; i<6; i++){
            
            Coordinate adj_cord = board.adjacent_cell(position, i);

            //if not null, and there's an ant in the position of the same colour, count
            if(adj_cord!= null && board.some_ant_is_at(adj_cord) && board.ant_at(adj_cord).getColour() == colour){
                antCount++;
                //System.out.println("Surround check: " + adj_cord.getX() + " " + adj_cord.getY() + " ant check:" + board.some_ant_is_at(adj_cord));
            }
        }
        
        return antCount;
    }
    
    //untested, double check for correctness
    public void check_for_surrounded_ant_at(Coordinate position){
        
        if(board.some_ant_is_at(position)){
            Ant ant = board.ant_at(position);
            
            if(adjacent_ants(position, other_color(ant.getColour())) >=5){
                board.kill_ant_at(position);
                if(ant.hasFood()){
                    int foodAt = board.food_at(position);
                    board.set_food_at(position, foodAt+3+1);
                }
                else{
                    int foodAt = board.food_at(position);
                    board.set_food_at(position, foodAt+3+0); 
                }
            }
        }
    }
    
    //DOUBLE CHECK THIS, not sure what this exactly does, also untested
    public void check_for_surrounded_ants(Coordinate position){
        
        
        check_for_surrounded_ant_at(position);
        
        for(int i=0; i<6; i++){
            check_for_surrounded_ant_at(board.adjacent_cell(position, i));
        }
    }
    
    
    

}
