package antgame;

import instructions.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Class representing the Match to be played.
 */
public class Match implements Runnable {

    private final int REST_LENGTH = 14;
    private final int MATCH_LENGTH = 300000;

    private Player player1;
    private Player player2;
    private GameBoard board;
    private Result result;
    private boolean isDone;
    private int round = 0;
    private int numOfRedAnts;
    private int numOfBlackAnts;
    Ant[] ants;

    ArrayList<Integer> s = new ArrayList<>();
    Integer seed = 12345;
    static int rngCalls = 0;

    public Match(Player p1, Player p2, GameBoard b) {
        int totalAnts;
        ArrayList<Coordinate> redAntHillCoordinates;
        ArrayList<Coordinate> blackAntHillCoordinates;

        isDone = false;
        player1 = p1;
        player2 = p2;
        board = b;
        numOfRedAnts = b.numOfAntHillCells(Colour.RED);
        numOfBlackAnts = b.numOfAntHillCells(Colour.BLACK);
        totalAnts = numOfRedAnts + numOfBlackAnts;
        ants = new Ant[totalAnts];

        s.add(seed);
        for (int i = 0; i < 10; i++) {
            Long s_n = (long) s.get(s.size() - 1);
            s_n *= 22695477;
            s_n = s_n % Integer.MAX_VALUE;
            s_n++;
            s.add(s_n.intValue());
        }

        for (int i = 0; i < numOfRedAnts; i++) {
            ants[i] = new Ant(Colour.RED);
        }
        for (int i = numOfRedAnts; i < totalAnts; i++) {
            ants[i] = new Ant(Colour.BLACK);
        }
        redAntHillCoordinates = b.getAntHillCoordinates(Colour.RED);
        blackAntHillCoordinates = b.getAntHillCoordinates(Colour.BLACK);

        for (int i = 0; i < numOfRedAnts; i++) {
            ants[i].setCurrentPosition(redAntHillCoordinates.get(0));
            
            board.getCellAtPosition(redAntHillCoordinates.get(0)).setOccupied(ants[i].getId());
            board.getCellAtPosition(redAntHillCoordinates.get(0)).setAntColour(Colour.RED);
            redAntHillCoordinates.remove(0);
        }
        for (int i = numOfRedAnts; i < totalAnts; i++) {
            ants[i].setCurrentPosition(blackAntHillCoordinates.get(0));
            board.getCellAtPosition(blackAntHillCoordinates.get(0)).setOccupied(ants[i].getId());
            board.getCellAtPosition(blackAntHillCoordinates.get(0)).setAntColour(Colour.BLACK);

            blackAntHillCoordinates.remove(0);
        }
    }

    public Result start() {
        int p1Food = 0;
        int p2Food = 0;
        Result matchResult;
        
        int delay = 3000;

        while (round < MATCH_LENGTH) {
          
            for (Ant ant : ants) {
                try {
                    step(ant);
                } catch (Exception ex) {
                    Logger.getLogger(Match.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            round++;
            //Thread.sleep(60);
        }

        isDone = true;
        //this.board.printBoard();

        p1Food = this.board.foodAtAntHill(Colour.RED);
        p2Food = this.board.foodAtAntHill(Colour.BLACK);

        if (p1Food >= p2Food) {
            matchResult = new Result(this.player1, this.player2, p1Food == p2Food);
        } else {
            matchResult = new Result(this.player2, this.player1, p1Food == p2Food);
        }

        return matchResult;
    }

    private void step(Ant ant) throws Exception {
        Colour antColour = ant.getColour();
        Colour foeColour = ant.getFoeColour();
        Cell currentCell = board.getCellAtPosition(ant.getCurrentPosition());
        int currentState = ant.getState();
        int nextState = ant.getState();
        AntBrain antBrain;
        Instruction instToExec;

        if (antColour.equals(Colour.RED)) {
            antBrain = player1.getAntBrain();
        } else {
            antBrain = player2.getAntBrain();
        }
        
        instToExec = antBrain.getInstructionAt(currentState);

        if (ant.getIsAlive()) {
            if (ant.getResting() > 0) {
                ant.decResting();
            } else {
                if (instToExec instanceof Sense) {
                    Sense sense = (Sense) instToExec;
                    Cell cellToSenseIn = board.getCellAtCurrentPositionPlusDirection(ant.getCurrentPosition(), ant.getDirection(), sense.getSenseDirection());
                    int cellOccupiedBy = cellToSenseIn.getOccupier();
                    if (sense.getCond().getCondition().equals(ConditionType.Friend)) {
                        if (cellOccupiedBy < 0) {
                            nextState = sense.getFalseState();
                        } else if (ants[cellOccupiedBy].getColour().equals(antColour)) {
                            nextState = sense.getTrueState();
                        } else {
                            nextState = sense.getFalseState();
                        }
                    } else if (sense.getCond().getCondition().equals(ConditionType.Foe)) {
                        if (cellOccupiedBy < 0) {
                            nextState = sense.getFalseState();
                        } else if (ants[cellOccupiedBy].getColour().equals(foeColour)) {
                            nextState = sense.getTrueState();
                        } else {
                            nextState = sense.getFalseState();
                        }
                    } else if (sense.getCond().getCondition().equals(ConditionType.FriendWithFood)) {
                        if (cellOccupiedBy < 0) {
                            nextState = sense.getFalseState();
                        } else if (ants[cellOccupiedBy].getColour().equals(antColour)) {
                            if (ants[cellOccupiedBy].hasFood()) {
                                nextState = sense.getTrueState();
                            } else {
                                nextState = sense.getFalseState();
                            }
                        } else {
                            nextState = sense.getFalseState();
                        }
                    } else if (sense.getCond().getCondition().equals(ConditionType.FoeWithFood)) {
                        if (cellOccupiedBy < 0) {
                            nextState = sense.getFalseState();
                        } else if (ants[cellOccupiedBy].getColour().equals(foeColour)) {
                            if (ants[cellOccupiedBy].hasFood()) {
                                nextState = sense.getTrueState();
                            } else {
                                nextState = sense.getFalseState();
                            }
                        } else {
                            nextState = sense.getFalseState();
                        }
                    } else if (sense.getCond().getCondition().equals(ConditionType.Food)) {
                        if (cellToSenseIn.containsFood()) {
                            nextState = sense.getTrueState();
                        } else {
                            nextState = sense.getFalseState();
                        }
                    } else if (sense.getCond().getCondition().equals(ConditionType.Rock)) {
                        if (cellToSenseIn.getTerrain().equals(Terrain.ROCKY)) {
                            nextState = sense.getTrueState();
                        } else {
                            nextState = sense.getFalseState();
                        }
                    } else if (sense.getCond().getCondition().equals(ConditionType.Marker)) {
                        if (cellToSenseIn.containsMarker(antColour, sense.getCond().getMark())) {
                            nextState = sense.getTrueState();
                        } else {
                            nextState = sense.getFalseState();
                        }
                    } else if (sense.getCond().getCondition().equals(ConditionType.FoeMarker)) {
                        if (cellToSenseIn.containsMarker(foeColour, sense.getCond().getMark())) {
                            nextState = sense.getTrueState();
                        } else {
                            nextState = sense.getFalseState();
                        }
                    } else if (sense.getCond().getCondition().equals(ConditionType.Home)) {
                        if (cellToSenseIn.isAnthillFor(antColour)) {
                            nextState = sense.getTrueState();
                        } else {
                            nextState = sense.getFalseState();
                        }
                    } else if (sense.getCond().getCondition().equals(ConditionType.FoeHome)) {
                        if (cellToSenseIn.isAnthillFor(foeColour)) {
                            nextState = sense.getTrueState();
                        } else {
                            nextState = sense.getFalseState();
                        }
                    }
                } else if (instToExec instanceof Mark) {
                    Mark mark = (Mark) instToExec;
                    currentCell.addMarker(antColour, mark.getMarkToLeave());
                    nextState = mark.getStateToGoTo();
                } else if (instToExec instanceof Unmark) {
                    Unmark unmark = (Unmark) instToExec;
                    currentCell.removeMarker(antColour, unmark.getMarkToRemove());
                    nextState = unmark.getStateToGoTo();
                } else if (instToExec instanceof Pickup) {
                    Pickup pickup = (Pickup) instToExec;
                    if (currentCell.containsFood()) {
                        if (!ant.hasFood()) {
                            currentCell.decrementFoodInCell();
                            ant.setHasFood(true);
                            nextState = pickup.getTrueState();
                        } else {
                            nextState = pickup.getFalseState();
                        }
                    } else {
                        nextState = pickup.getFalseState();
                    }
                } else if (instToExec instanceof Drop) {
                    Drop drop = (Drop) instToExec;
                    if (ant.hasFood() && currentCell.isAnthillFor(ant.getColour())) {
                        currentCell.incrementFoodInCell();
                        ant.setHasFood(false);
                        nextState = drop.getStateToGoTo();
                    } else {
                        nextState = drop.getStateToGoTo();
                    }
                } else if (instToExec instanceof Turn) {
                    Turn turn = (Turn) instToExec;
                    if (turn.getTurnDirection().equals(TurnDirection.Left)) {
                        int newDirection = (ant.getDirection() + 5) % 6;
                        ant.setDirection(newDirection);
                        nextState = turn.getStateToGoTo();
                    } else if (turn.getTurnDirection().equals(TurnDirection.Right)) {
                        int newDirection = (ant.getDirection() + 1) % 6;
                        ant.setDirection(newDirection);
                        nextState = turn.getStateToGoTo();
                    } else {
                        throw new Exception("Can only turn left or right");
                    }
                } else if (instToExec instanceof Move) {
                    Move move = (Move) instToExec;
                    Cell cellToMoveTo = board.getCellAtCurrentPositionPlusDirection(ant.getCurrentPosition(), ant.getDirection(), SenseDirection.Ahead);
                    if (cellToMoveTo.getTerrain().equals(Terrain.ROCKY) || cellToMoveTo.getOccupier() > -1) {
                        nextState = move.getStateToGoToIfBlocked();
                    } else {
                        //System.out.println("current cell occ = "+currentCell.getOccupier());
                        currentCell.removeOccupation();
                        currentCell.removeAntColour(); //reset colour
                        cellToMoveTo.setOccupied(ant.getId());
                        //System.out.println("cell to move to = "+cellToMoveTo.getOccupier());

                        cellToMoveTo.setAntColour(ant.getColour());
                        ant.setCurrentPosition(cellToMoveTo.getCoordinate());
                        nextState = move.getStateToGoToIfClear();
                        ant.setResting(REST_LENGTH);
                        checkForSurroundedAnts(cellToMoveTo);
                    }
                } else if (instToExec instanceof Flip) {
                    Flip flip = (Flip) instToExec;
                    int randomNumber = randomNumberGen(flip.getN());
                    if (randomNumber == 0) {
                        nextState = flip.getNisOState();
                    } else {
                        nextState = flip.getnGTOState();
                    }
                } else {
                    throw new Exception("Unexpected instruction");
                }
            }
        }

        ant.setState(nextState);
    }

    public int randomNumberGen(int n) {
        /*
        
         TRYING TO DO MFB's RBG - it's a pain
        
         Long s_n = (long) s.get(s.size()-1);
         s_n*=22695477;
         s_n++;
         s_n = s_n%Integer.MAX_VALUE;
         s.add(s_n.intValue());
        
         for (int i = 0; i < s.size(); i++) {
         System.out.println("S_"+i+" = "+s.get(i));
         }
        
         int toReturn = s.get(rngCalls+4);
         System.out.println("s_"+rngCalls+"+4 ="+toReturn);
         System.out.print(toReturn+" div 65536 = ");
         toReturn = toReturn/65536;
         System.out.println(toReturn);
         System.out.print(toReturn+" mod 16384 = ");
         toReturn = toReturn%16384;
         System.out.println(toReturn);
         System.out.print(toReturn+" mod n = ");
         toReturn = toReturn%n;
         System.out.println(toReturn);
        
         rngCalls++;
        
         return toReturn;
         */

        // Java random number gen for now.
        double toReturn = Math.random();
        toReturn *= n;
        return (int) toReturn;
    }

    public void checkForSurroundedAnts(Cell c) throws Exception {
        checkForSurroundedAntAt(c);
        for (int d = 0; d < 6; d++) {
            Cell lookingIn = board.getCellAtCurrentPositionPlusDirection(c.getCoordinate(), d, SenseDirection.Ahead);
            checkForSurroundedAntAt(lookingIn);
        }
    }

    public void checkForSurroundedAntAt(Cell c) throws Exception {
        if (c.isOccupied()) {
            Ant a = ants[c.getOccupier()];
            if (adjacentAnts(c, a.getFoeColour()) >= 5) {
                a.setIsAlive(false);
                c.setOccupied(-1);
                c.setFoodCount(c.getFoodCount() + 3);
                if (a.hasFood()) {
                    c.setFoodCount(c.getFoodCount() + 1);
                }
            }
        }
    }

    public int adjacentAnts(Cell cell, Colour foeColour) throws Exception {
        int foeCount = 0;
        for (int d = 0; d < 6; d++) {
            Cell lookingIn = board.getCellAtCurrentPositionPlusDirection(cell.getCoordinate(), d, SenseDirection.Ahead);
            if (lookingIn.isOccupied()) {
                if (ants[lookingIn.getOccupier()].getColour().equals(foeColour)) {
                    foeCount++;
                }
            }
        }
        return foeCount;
    }

    /**
     * Get the ants for the game. Used in testing
     * @return Ants used in the game.
     */
    public Ant[] getAntArray() {
        return ants;
    }

    /**
     * Get the Game Board.
     * @return Game Board
     */
    public GameBoard getBoard() {
        return board;
    }
    
    @Override
    public void run() {
        try {
            start();
        } catch (Exception ex) {
            System.out.println("System, opp smatch error");
        }
    }
    
    public boolean isGameDone(){
        return isDone;
        
    }
    
    public int getPlayerFoodCount(Colour colour){
        return board.foodAtAntHill(colour);
    }
}
