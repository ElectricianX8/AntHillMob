/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package instructions;

import antgame.Condition;

/**
 *
 * @author alisaleem
 */
public class Sense extends Instruction {
    private int senseDirection;
    private int trueState;
    private int falseState;
    private Condition cond;
    
    public Sense(){
        senseDirection = 0;
        trueState = 0;
        falseState = 0;
        cond = null;
    }
    
    public Sense(int senseDirection, int trueState, int falseState, Condition cond){
        this.senseDirection = senseDirection;
        this.trueState = trueState;
        this.falseState = falseState;
        this.cond = cond;
    }

    /**
     * @return the senseDirection
     */
    public int getSenseDirection() {
        return senseDirection;
    }

    /**
     * @param senseDirection the senseDirection to set
     */
    public void setSenseDirection(int senseDirection) {
        this.senseDirection = senseDirection;
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

    /**
     * @return the cond
     */
    public Condition getCond() {
        return cond;
    }

    /**
     * @param cond the cond to set
     */
    public void setCond(Condition cond) {
        this.cond = cond;
    }
    
    public String toString() {
        return "Sense";
    }
}
