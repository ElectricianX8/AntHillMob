/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;




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

    
    public boolean cell_matches(Coordinate position, Condition condition, Colour colour) {

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

}
