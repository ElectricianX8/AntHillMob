/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

import instructions.*;
import java.util.ArrayList;

/**
 *
 * Top-level class for managing game
 */
public class Game implements Runnable {

    Player redPlayer;
    Player blackPlayer;
    boolean isDone;
    ArrayList<Ant> ants;
    GameBoard board;
    int delay;
    
    ArrayList<Integer> s = new ArrayList<>();
    Integer seed = 12345;
    static int rngCalls = 0;
    

    public Game(Player redPlayer, Player blackPlayer, GameBoard board) {
        this.redPlayer = redPlayer;
        this.blackPlayer = blackPlayer;
        this.board = board;
        isDone = false;
        initiateAnts();
        initiateRNG();
        delay = 0;
    }
    
   
    private void initiateRNG(){
        s.add(seed);
        for (int i = 0; i < 10; i++) {
            Long s_n = (long) s.get(s.size()-1);
            s_n = (s_n*22695477+1)&(Integer.MAX_VALUE);
            s.add(s_n.intValue());
        }
    }
    
    private void initiateAnts(){
        
        ants = new ArrayList<Ant>();
        for(Coordinate cord : board.getAnthills()){
            if(board.anthill_at(cord, Colour.RED)){
               Ant ant = new Ant(Colour.RED, 0, cord.getY(), cord.getX());
               board.set_ant_at(cord, ant);
               ants.add(ant);
            }
            else if(board.anthill_at(cord,Colour.BLACK)){
               Ant ant = new Ant(Colour.BLACK, 0, cord.getY(), cord.getX());
               board.set_ant_at(cord, ant);
               ants.add(ant);
            }
            //check if not anthill for error catching purposes?
        }
        
    }

    public Colour other_color(Colour colour) {

        if (colour == Colour.RED) {
            return Colour.BLACK;
        } else {
            return Colour.RED;
        }

    }

    public int turn(TurnDirection turnDirection, int direction) {

        if (turnDirection == TurnDirection.Left) {
            return (direction + 5) % 6;
        } else {
            return (direction + 1) % 6;
        }
    }

    public Coordinate sensed_cell(Coordinate position, int direction, SenseDirection sense) {

        if (sense == SenseDirection.Here) {
            return position;
        } else if (sense == SenseDirection.Ahead) {
            return board.adjacent_cell(position, direction);
        } else if (sense == SenseDirection.LeftAhead) {
            return board.adjacent_cell(position, turn(TurnDirection.Left, direction));
        } else if (sense == SenseDirection.RightAhead) {
            return board.adjacent_cell(position, turn(TurnDirection.Right, direction));
        } else {
            return null;
        }
    }

    public boolean cell_matches(Coordinate position, Condition condition, Colour colour) {

        
        if (board.rocky(position)) {
            return condition.getCondition() == ConditionType.Rock;
        } else {
            if (condition.getCondition() == ConditionType.Friend) {
                //check first for null, then colour?
                return board.some_ant_is_at(position) && board.ant_at(position).getColour() == colour;
            } else if (condition.getCondition() == ConditionType.Foe) {
                return board.some_ant_is_at(position) && board.ant_at(position).getColour() != colour;
            } else if (condition.getCondition() == ConditionType.FriendWithFood) {
                return board.some_ant_is_at(position) && board.ant_at(position).getColour() == colour && board.ant_at(position).hasFood();
            } else if (condition.getCondition() == ConditionType.FoeWithFood) {
                return board.some_ant_is_at(position) && board.ant_at(position).getColour() != colour && board.ant_at(position).hasFood();
            } else if (condition.getCondition() == ConditionType.Food) {
                return board.food_at(position) > 0;
            } else if (condition.getCondition() == ConditionType.Rock) {
                return false;
            } else if (condition.getCondition() == ConditionType.Marker) {
                return board.check_marker_at(position, colour, condition.getMark());
            } else if (condition.getCondition() == ConditionType.FoeMarker) {
                return board.check_any_marker_at(position, other_color(colour));
            } else if (condition.getCondition() == ConditionType.Home) {
                return board.anthill_at(position, colour);
            } else if (condition.getCondition() == ConditionType.FoeHome) {
                return board.anthill_at(position, other_color(colour));
            } else {
                return false;
            }
        }
    }

    public int adjacent_ants(Coordinate position, Colour colour) {

        int antCount = 0;

        //check all directions
        for (int i = 0; i < 6; i++) {

            Coordinate adj_cord = board.adjacent_cell(position, i);

            //if not null, and there's an ant in the position of the same colour, count
            if (adj_cord != null && board.some_ant_is_at(adj_cord) && board.ant_at(adj_cord).getColour() == colour) {
                antCount++;
                //System.out.println("Surround check: " + adj_cord.getX() + " " + adj_cord.getY() + " ant check:" + board.some_ant_is_at(adj_cord));
            }
        }

        return antCount;
    }
    
    private void kill_ant(Ant ant){
        
        board.clear_ant_at(ant.getCurrentPosition());
        ant.setCurrentPosition(null);
        ant.setIsAlive(false);
    }
    
    


    public void check_for_surrounded_ant_at(Coordinate position) {

        if (board.some_ant_is_at(position)) {
            Ant ant = board.ant_at(position);

            if (adjacent_ants(position, other_color(ant.getColour())) >= 5) {
                kill_ant(ant);
                if (ant.hasFood()) {
                    int foodAt = board.food_at(position);
                    board.set_food_at(position, foodAt + 3 + 1);
                } else {
                    int foodAt = board.food_at(position);
                    board.set_food_at(position, foodAt + 3 + 0);
                }
            }
        }
    }

    public void check_for_surrounded_ants(Coordinate position) {

        check_for_surrounded_ant_at(position);

        for (int i = 0; i < 6; i++) {
            check_for_surrounded_ant_at(board.adjacent_cell(position, i));
        }
    }

    public GameBoard getBoard() {
        return board;
    }

    private AntBrain getPlayerBrain(Colour colour) {

        AntBrain antBrain;
        if (colour == Colour.RED) {
            antBrain = redPlayer.getAntBrain();
            return antBrain;
        } else {
            antBrain = blackPlayer.getAntBrain();
            return antBrain;
        }
    }

    public void step(Ant ant) throws Exception {

        if (ant.IsAlive()){
            
            Coordinate currentPos = ant.getCurrentPosition();

            if (ant.getResting() > 0) {
                ant.setResting(ant.getResting() - 1);
            } else {
                int currentState = ant.getState();
                int nextState = 0;
                AntBrain antBrain = getPlayerBrain(ant.getColour());
                Instruction instruction = antBrain.getInstructionAt(currentState);
                
                if(instruction instanceof Sense){
                    Sense sense = (Sense) instruction;
                    Coordinate sensedPosition = sensed_cell(currentPos, ant.getDirection(), sense.getSenseDirection());
                    if(cell_matches(sensedPosition, sense.getCond(), ant.getColour())){ //if sensing true
                        nextState = sense.getTrueState();
                    } else {
                        nextState = sense.getFalseState();
                    }
                }
                else if(instruction instanceof Mark){
                    Mark mark = (Mark) instruction;
                    board.set_marker_at(currentPos, ant.getColour(), mark.getMarkToLeave());
                    nextState = mark.getStateToGoTo();
                }
                else if(instruction instanceof Unmark){
                    Unmark unmark = (Unmark) instruction;
                    board.clear_marker_at(currentPos, ant.getColour(), unmark.getMarkToRemove());
                    nextState = unmark.getStateToGoTo();
                }
                else if(instruction instanceof Pickup){
                    Pickup pickup = (Pickup) instruction;
                    //|| board.anthill_at(currentPos, other_color(ant.getColour())) add this to prevent picking up from enemy anthill
                    //only prevents picking up from own anthill now, but that's not specified in spec
                    if(ant.hasFood() || board.food_at(currentPos) == 0 || board.anthill_at(currentPos, ant.getColour()) ){
                        nextState = pickup.getFalseState();
                    } else {
                        board.set_food_at(currentPos, board.food_at(currentPos)-1);
                        ant.setHasFood(true);
                        nextState = pickup.getTrueState();
                    }
                }
                else if(instruction instanceof Drop){
                    Drop drop = (Drop) instruction;
                    // && board.anthill_at(currentPos, ant.getColour()) prevent dropping outside anthill, not in spec
                    if(ant.hasFood() && board.anthill_at(currentPos, ant.getColour())){
                        board.set_food_at(currentPos, board.food_at(currentPos)+1);
                        ant.setHasFood(false);
                    }
                    nextState = drop.getStateToGoTo();           
                }
                else if(instruction instanceof Turn){
                    Turn turn = (Turn) instruction;
                    ant.setDirection(turn(turn.getTurnDirection(), ant.getDirection()));
                    nextState = turn.getStateToGoTo();
                }
                else if(instruction instanceof Move){
                    Move move = (Move) instruction;
                    Coordinate nextPosition = board.adjacent_cell(currentPos, ant.getDirection());
                    
                    if(board.rocky(nextPosition) || board.some_ant_is_at(nextPosition)){ 
                        nextState = move.getStateToGoToIfBlocked(); //if blocked
                    } else {
                        board.clear_ant_at(currentPos);
                        board.set_ant_at(nextPosition, ant);
                        ant.setCurrentPosition(nextPosition);
                        ant.setResting(14);
                        nextState = move.getStateToGoToIfClear();
                        //System.out.println("Initial Cord: X (" + currentPos.getX() + ") Y(" + currentPos.getY()+ "), Moved to: X(" + nextPosition.getX() + ") Y(" + nextPosition.getY()+ ")");
                        check_for_surrounded_ants(nextPosition);     
                        if(delay>0){
                           Thread.sleep(delay);
                        }
                         
                    }
                }
                else if(instruction instanceof Flip){
                    Flip flip = (Flip) instruction;
                    
                    if(randomNumberGen(flip.getN()) == 0){
                        nextState = flip.getNisOState();
                    } else {
                        nextState = flip.getnGTOState();
                    }
                }
                else{
                    System.out.println(instruction);
                    throw new Exception("Unexpected instruction");
                }
                
                ant.setState(nextState);
            }
        } else {
            //System.out.println("Ant dead");
        }
    }
    
    
    public Result start() throws Exception{
        
        int p1Food = 0;
        int p2Food = 0;
        Result matchResult;
        
        int round = 0;
        while (round < 300000) {
            for (Ant ant : ants) {
                //alive check
                step(ant);
            }



            round++;
        }
        
        isDone = true;
        System.out.print("Game has finished, yay");
        

        p1Food = getPlayerFoodCount(Colour.RED);
        p2Food = getPlayerFoodCount(Colour.BLACK);

        if (p1Food >= p2Food) {
            matchResult = new Result(redPlayer, blackPlayer, p1Food == p2Food);
        } else {
            matchResult = new Result(blackPlayer, redPlayer, p1Food == p2Food);
        }

        return matchResult;
        
    }
    
    
    public int getPlayerFoodCount(Colour colour){
        
        int count = 0;
        for(Coordinate antHill : board.getAnthills()){
            if(board.anthill_at(antHill, colour) && board.food_at(antHill) > 0){
                count+= board.food_at(antHill);
            }
        }
        
        return count;
        
    }
    
    public int getPlayerAntCount(Colour colour){
        
        int count = 0;
        for(Ant ant : ants){
            if(ant.IsAlive() && ant.getColour() == colour){
                count++;
            }
        }
        return count;
    }
    
    public void changeDelay(int delay){
        this.delay = delay;
    }
    
    
    public int randomNumberGen(int n) {
        
        /*
        Long s_n = (long) s.get(s.size()-1);
         s_n = (s_n*22695477+1)&(1073741823);
         s.add(s_n.intValue());
        
         //for (int i = 0; i < s.size(); i++) {
         //System.out.println("S_"+i+" = "+s.get(i)); //HEAVY RESOURCE HOG
         //}
        
         int toReturn = s.get(rngCalls+4);
         toReturn = toReturn/65536;
         toReturn = toReturn%16384;
         toReturn = toReturn%n;
        
         rngCalls++;
        
         return toReturn;
                */
              double toReturn = Math.random();
        toReturn *= n;
        return (int)toReturn;
    }

    @Override
    public void run() {
        try {
            start();
        } catch (Exception ex) {
            System.out.println("oops, game crashed");
        }
    }

    public boolean isGameDone() {
        return isDone;
    }

}
