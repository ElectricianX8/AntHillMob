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
public class Pickup extends Instruction {
    private int trueState;
    private int falseState;
    
    Pickup(){
        trueState = 0;
        falseState = 0;
    }
    
    Pickup(int trueState, int falseState){
        this.trueState = trueState;
        this.falseState = falseState;
    }

    /**
     * @return the trueState
     */
    public int getTrueState() {
        return trueState;
    }

    /**
     * @param trueState the trueState to set
     */
    public void setTrueState(int trueState) {
        this.trueState = trueState;
    }

    /**
     * @return the falseState
     */
    public int getFalseState() {
        return falseState;
    }

    /**
     * @param falseState the falseState to set
     */
    public void setFalseState(int falseState) {
        this.falseState = falseState;
    }
    
    public String toString() {
        return "Pickup";
    }
}
