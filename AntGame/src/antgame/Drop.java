/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

/**
 *
 * @author alisaleem
 */
public class Drop {
    private int stateToGoTo;
  
    public Drop(){
        stateToGoTo = 0;
    }
    
    public Drop(int s){
        stateToGoTo = s;
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
        return "Drop";
    }
}
