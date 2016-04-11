/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instructions;

import antgame.TurnDirection;

/**
 *
 * @author alisaleem
 */
public class Turn {
    private TurnDirection turnDirection;
    private int stateToGoTo;
    
    public Turn(){
        turnDirection = null;
        stateToGoTo = 0;
    }
    
    public Turn(TurnDirection td, int s){
        turnDirection = td;
        stateToGoTo = s;
    }

    /**
     * @return the turnDirection
     */
    public TurnDirection getTurnDirection() {
        return turnDirection;
    }

    /**
     * @param turnDirection the turnDirection to set
     */
    public void setTurnDirection(TurnDirection turnDirection) {
        this.turnDirection = turnDirection;
    }

    /**
     * @return the stateToGoTo
     */
    public int getStateToGoTo() {
        return stateToGoTo;
    }

    /**
     * @param stateToGoTo the stateToGoTo to set
     */
    public void setStateToGoTo(int stateToGoTo) {
        this.stateToGoTo = stateToGoTo;
    }
    
    public String toString() {
        return "Turn";
    }
}
