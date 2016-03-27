/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instructions;

/**
 *
 * @author alisaleem
 */
public class Unmark extends Instruction {
    private int markToRemove;
    private int stateToGoTo;
    
    public Unmark(){
        markToRemove = 0;
        stateToGoTo = 0;
    }
    
    public Unmark(int markToRemove, int stateToGoTo){
        this.markToRemove = markToRemove;
        this.stateToGoTo = stateToGoTo;
    }

    /**
     * @return the markToRemove
     */
    public int getMarkToRemove() {
        return markToRemove;
    }

    /**
     * @param markToRemove the markToRemove to set
     */
    public void setMarkToRemove(int markToRemove) {
        this.markToRemove = markToRemove;
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
        return "Unmark";
    }
    
    
}
