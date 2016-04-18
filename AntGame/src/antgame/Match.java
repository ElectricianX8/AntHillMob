package antgame;

import instructions.*;
import java.util.ArrayList;

/**
 * Class representing the Match to be played.
 */
public class Match {

    private Player player1;
    private Player player2;
    private GameBoard board;
    private Result result;
    private int round = 0;
    private int numOfRedAnts;
    private int numOfBlackAnts;
    Ant[] ants;

    public Match(Player p1, Player p2, GameBoard b) {
        int totalAnts;
        ArrayList<Coordinate> redAntHillCoordinates;
        ArrayList<Coordinate> blackAntHillCoordinates;

        player1 = p1;
        player2 = p2;
        board = b;
        numOfRedAnts = b.numOfAntHillCells(Colour.RED);
        numOfBlackAnts = b.numOfAntHillCells(Colour.BLACK);
        totalAnts = numOfRedAnts + numOfBlackAnts;
        ants = new Ant[totalAnts];

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
            redAntHillCoordinates.remove(0);
        }
        for (int i = numOfRedAnts; i < totalAnts; i++) {
            ants[i].setCurrentPosition(blackAntHillCoordinates.get(0));
            blackAntHillCoordinates.remove(0);
        }

    }

    public Result start() throws Exception {
        int p1Food = 0;
        int p2Food = 0;
        Result matchResult;

        while (round < 300000) {
            for (Ant ant : ants) {
                step(ant);
            }
            round++;
        }
        
        this.board.printBoard();

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
        int nextState = 0;
        AntBrain antBrain;
        Instruction instToExec;

        if (antColour.equals(Colour.RED)) {
            antBrain = player1.getAntBrain();
        } else {
            antBrain = player2.getAntBrain();
        }

        instToExec = antBrain.getInstructionAt(currentState);

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
            if (ant.hasFood()) {
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
            if (cellToMoveTo.getTerrain().equals(Terrain.CLEAR)) {
                currentCell.removeOccupation();
                ant.setCurrentPosition(cellToMoveTo.getCoordinate());
                nextState = move.getStateToGoToIfClear();
            } else {
                nextState = move.getStateToGoToIfBlocked();
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

        ant.setState(nextState);
    }

    private int randomNumberGen(int n) {
        // Java random number gen for now.
        double toReturn = Math.random();
        toReturn *= n;
        return (int)toReturn;
    }
}
