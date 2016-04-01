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
public class Mark extends Instruction {
    private int markToLeave;
    private int stateToGoTo;
    
    public Mark(){
        markToLeave = 0;
        stateToGoTo = 0;
    }
    
    public Mark(int markToLeave, int stateToGoTo){
        this.markToLeave = markToLeave;
        this.stateToGoTo = stateToGoTo;
    }

    /**
     * @return the markToLeave
     */
    public int getMarkToLeave() {
        return markToLeave;
    }

    /**
     * @param markToLeave the markToLeave to set
     */
    public void setMarkToLeave(int markToLeave) {
        this.markToLeave = markToLeave;
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
        return "Mark";
    }
}
