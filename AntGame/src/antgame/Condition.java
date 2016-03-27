/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package antgame;

/**
 *
 * 
 */
public class Condition {
    
    private ConditionType condition;
    private int markNumber;

    public Condition(ConditionType condition){
        this.condition = condition;
        markNumber = 0;
    }
    
    public Condition(ConditionType condition, int mark){
        this.condition = condition;
        markNumber = mark;
    }
    
    public ConditionType getCondition(){
        return condition;
    }

    public int getMark(){
        return markNumber;
    }
}
