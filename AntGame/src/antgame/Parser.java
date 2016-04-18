/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

import instructions.Instruction;
import instructions.*;
import java.util.ArrayList;
import tokens.*;

/**
 *
 * @author oliverthomas
 */
class Parser {

    public Parser() {}
    
    public AntBrain parseAntBrain(ArrayList<Token> lexAntBrain) throws ParsingException, NotValidInstructionException {
        String AntBrainName;
        Instruction[] parsedInstructionsToReturn;
        ArrayList<Instruction> parsedInstructions = new ArrayList<>();
        
        if (lexAntBrain.get(0) instanceof TokenName) {
            TokenName n = (TokenName) lexAntBrain.get(0);
            lexAntBrain.remove(0);
            AntBrainName = n.getName();
        } else {
            throw new ParsingException("Ant Brain must begin with it's name");
        }
        
        while (lexAntBrain.size() > 0) {
            if (lexAntBrain.get(0) instanceof TokenInstruction) {
                TokenInstruction inst = (TokenInstruction) lexAntBrain.get(0);
                lexAntBrain.remove(0);
                if (inst.getInstruction().equals("Sense")) {
                    if (lexAntBrain.get(0) instanceof TokenDirection) {
                        SenseDirection d;
                        TokenDirection dir = (TokenDirection) lexAntBrain.get(0);
                        lexAntBrain.remove(0);
                        switch (dir.getDirection()) {
                            case "Ahead":
                                d = SenseDirection.Ahead;
                                break; 
                            case "LeftAhead":
                                d = SenseDirection.LeftAhead;
                                break;
                            case "RightAhead":
                                d = SenseDirection.RightAhead;
                                break;
                            case "Here":
                                d = SenseDirection.Here;
                                break;
                            default:
                                throw new ParsingException("Invalid SenseDirection Input");
                        }
                        if (lexAntBrain.get(0) instanceof TokenInt) {
                            TokenInt st1 = (TokenInt) lexAntBrain.get(0);
                            lexAntBrain.remove(0);
                            if (lexAntBrain.get(0) instanceof TokenInt) {
                                TokenInt st2 = (TokenInt) lexAntBrain.get(0);
                                lexAntBrain.remove(0);
                                if (lexAntBrain.get(0) instanceof TokenCondition) {
                                    Condition c;
                                    TokenCondition cond = (TokenCondition) lexAntBrain.get(0);
                                    lexAntBrain.remove(0);
                                    if (cond.getCondition().equals("Marker")) {
                                        if (lexAntBrain.get(0) instanceof TokenInt) {
                                            TokenInt mark = (TokenInt) lexAntBrain.get(0);
                                            lexAntBrain.remove(0);
                                            c = new Condition(ConditionType.Marker, mark.getNum());
                                            parsedInstructions.add(new Sense(d, st1.getNum(), st2.getNum(), c));
                                        } else {
                                            throw new ParsingException("Marker requires an int");
                                        }
                                    } else {
                                        ConditionType ct;
                                        switch(cond.getCondition()) {
                                            case "Friend":
                                                ct = ConditionType.Friend;
                                                break;
                                            case "Foe":
                                                ct = ConditionType.Foe;
                                                break;
                                            case "FriendWithFood":
                                                ct = ConditionType.FriendWithFood;
                                                break;
                                            case "FoeWithFood":
                                                ct = ConditionType.FoeWithFood;
                                                break;
                                            case "Food":
                                                ct = ConditionType.Food;
                                                break;
                                            case "Rock":
                                                ct = ConditionType.Rock;
                                                break;
                                            case "Marker":
                                                throw new ParsingException("Should have been caught in previous statement");
                                            case "FoeMarker":
                                                ct = ConditionType.FoeMarker;
                                                break;
                                            case "Home":
                                                ct = ConditionType.Home;
                                                break;
                                            case "FoeHome":
                                                ct = ConditionType.FoeHome;
                                                break;
                                            default:
                                                throw new ParsingException("Not a valid condition");
                                        }
                                        c = new Condition(ct);
                                        parsedInstructions.add(new Sense(d, st1.getNum(), st2.getNum(), c));
                                    }
                                } else {
                                    throw new ParsingException("Sense Instruction States not followed by Condition");
                                }
                            } else {
                                throw new ParsingException("Sense Instruction TrueState not followed by FalseState");
                            }
                        } else {
                            throw new ParsingException("Sense Instruction Direction not followed by Int");
                        }
                    }
                    else {
                        throw new ParsingException("Sense Instruction not followed by Direction");
                    }
                } else if (inst.getInstruction().equals("Mark")) {
                    if (lexAntBrain.get(0) instanceof TokenInt) {
                        TokenInt marker = (TokenInt) lexAntBrain.get(0);
                        lexAntBrain.remove(0);
                        if (lexAntBrain.get(0) instanceof TokenInt) {
                            TokenInt state = (TokenInt) lexAntBrain.get(0);
                            lexAntBrain.remove(0);
                            parsedInstructions.add(new Mark(marker.getNum(), state.getNum()));
                        } else {
                            throw new ParsingException("State required as 2nd param of Mark");
                        }
                    } else {
                        throw new ParsingException("int must follow Mark");
                    }
                } else if (inst.getInstruction().equals("Unmark")) {
                    if (lexAntBrain.get(0) instanceof TokenInt) {
                        TokenInt marker = (TokenInt) lexAntBrain.get(0);
                        lexAntBrain.remove(0);
                        if (lexAntBrain.get(0) instanceof TokenInt) {
                            TokenInt falseState = (TokenInt) lexAntBrain.get(0);
                            lexAntBrain.remove(0);
                            parsedInstructions.add(new Unmark(marker.getNum(), falseState.getNum()));
                        } else {
                            throw new ParsingException("State required in unmark inst");
                        }
                    } else {
                        throw new ParsingException("Marker to remove must follow unmark");
                    }
                } else if (inst.getInstruction().equals("PickUp")) {
                    if (lexAntBrain.get(0) instanceof TokenInt) {
                        TokenInt trueState = (TokenInt) lexAntBrain.get(0);
                        lexAntBrain.remove(0);
                        if (lexAntBrain.get(0) instanceof TokenInt) {
                            TokenInt falseState = (TokenInt) lexAntBrain.get(0);
                            lexAntBrain.remove(0);
                            parsedInstructions.add(new Pickup(trueState.getNum(), falseState.getNum()));
                        } else {
                            throw new ParsingException("False State required in unmark inst");
                        }
                    } else {
                        throw new ParsingException("true State must follow Pickup");
                    }
                } else if (inst.getInstruction().equals("Drop")) {
                    if (lexAntBrain.get(0) instanceof TokenInt) {
                        TokenInt state = (TokenInt) lexAntBrain.get(0);
                        lexAntBrain.remove(0);
                        parsedInstructions.add(new Drop(state.getNum()));
                    } else {
                        throw new ParsingException("State required after Drop");
                    }
                } else if (inst.getInstruction().equals("Turn")) {
                    if (lexAntBrain.get(0) instanceof TokenTurn) {
                        TurnDirection td;
                        TokenTurn turn = (TokenTurn) lexAntBrain.get(0);
                        switch(turn.getTurn()) {
                            case "Left":
                                td = TurnDirection.Left;
                                break;
                            case "Right":
                                td = TurnDirection.Right;
                                break;
                            default:
                                throw new ParsingException("Can only turn left or right");
                        }
                        lexAntBrain.remove(0);
                        if (lexAntBrain.get(0) instanceof TokenInt) {
                            TokenInt state = (TokenInt) lexAntBrain.get(0);
                            lexAntBrain.remove(0);
                            parsedInstructions.add(new Turn(td, state.getNum()));
                        } else {
                            throw new ParsingException("Turn reqs state to go to");
                        }
                    } else {
                        throw new ParsingException("Turn direction req after Turn");
                    }
                } else if (inst.getInstruction().equals("Move")) {
                    if (lexAntBrain.get(0) instanceof TokenInt) {
                        TokenInt trueState = (TokenInt) lexAntBrain.get(0);
                        lexAntBrain.remove(0);
                        if (lexAntBrain.get(0) instanceof TokenInt) {
                            TokenInt falseState = (TokenInt) lexAntBrain.get(0);
                            lexAntBrain.remove(0);
                            parsedInstructions.add(new Move(trueState.getNum(), falseState.getNum()));
                        } else {
                            throw new ParsingException("Move reqs False state");
                        }
                    } else {
                        throw new ParsingException("Move reqs True State");
                    }
                } else if (inst.getInstruction().equals("Flip")) {
                    if (lexAntBrain.get(0) instanceof TokenInt) {
                        TokenInt maxnum = (TokenInt) lexAntBrain.get(0);
                        lexAntBrain.remove(0);
                        if (lexAntBrain.get(0) instanceof TokenInt) {
                            TokenInt trueState = (TokenInt) lexAntBrain.get(0);
                            lexAntBrain.remove(0);
                            if (lexAntBrain.get(0) instanceof TokenInt) {
                                TokenInt falseState = (TokenInt) lexAntBrain.get(0);
                                lexAntBrain.remove(0);
                                parsedInstructions.add(new Flip(maxnum.getNum(), trueState.getNum(), falseState.getNum()));
                            } else {
                                throw new ParsingException("Flip reqs falseState");
                            }
                        } else {
                            throw new ParsingException("Flip reqs TrueState");
                        }
                    } else {
                        throw new ParsingException("Flip requires MaxNum");
                    }
                }
            } else {
                throw new ParsingException("Instruction expected");
            }
        }
        
        parsedInstructionsToReturn = parsedInstructions.toArray(new Instruction[parsedInstructions.size()]);

        return new AntBrain(AntBrainName, parsedInstructionsToReturn);
    }
    
}
