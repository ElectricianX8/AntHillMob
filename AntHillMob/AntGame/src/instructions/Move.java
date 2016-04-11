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
public class Move extends Instruction {

    private int stateToGoToIfClear;
    private int StateToGoToIfBlocked;

    public Move() {
        stateToGoToIfClear = 0;
        StateToGoToIfBlocked = 0;
    }

    public Move(int stateToGoToIfClear, int StateToGoToIfBlocked) {
        this.stateToGoToIfClear = stateToGoToIfClear;
        this.StateToGoToIfBlocked = StateToGoToIfBlocked;
    }

    /**
     * @return the stateToGoToIfClear
     */
    public int getStateToGoToIfClear() {
        return stateToGoToIfClear;
    }

    /**
     * @param stateToGoToIfClear the stateToGoToIfClear to set
     */
    public void setStateToGoToIfClear(int stateToGoToIfClear) {
        this.stateToGoToIfClear = stateToGoToIfClear;
    }

    /**
     * @return the StateToGoToIfBlocked
     */
    public int getStateToGoToIfBlocked() {
        return StateToGoToIfBlocked;
    }

    /**
     * @param StateToGoToIfBlocked the StateToGoToIfBlocked to set
     */
    public void setStateToGoToIfBlocked(int StateToGoToIfBlocked) {
        this.StateToGoToIfBlocked = StateToGoToIfBlocked;
    }
    
    public String toString() {
        return "Move";
    }
}
