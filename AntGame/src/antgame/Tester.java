/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package antgame;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Testing class that can be attached to the game instance to generate dumps of the state of the game at each round.
 */
public class Tester {

    /**
     * Default constructor.
     */
    public Tester(){
        
    }
    
    /**
     * Output the round state to a file.
     * @param board Current board/world of the game
     * @param roundNumber The round number.
     */
    public void outputRound(Cell[][] board, int roundNumber){
        
        
        String round = "After round " + roundNumber + "..." + Character.toString((char) 0x0a);
        
        for(int i=0; i<board.length;i++){
            for(int j=0; j<board[0].length;j++){
                Cell cell = board[i][j];
                round+=scanCell(cell, i, j);
                round+= Character.toString((char) 0x0a);
            }
        }
        
        round+=Character.toString((char) 0x0a);
        
        writeToFile(round);
        
    }
    
    
    //Scan a cell and format its state for outputting
    private String scanCell(Cell cell, int y, int x){
        
        
        String msg = "cell (" + x + ", " + y + "): ";
        
        if(cell.getTerrain()==Terrain.ROCKY){
            msg+="rock";
        } else{
            if(cell.getFoodCount()>0){
                msg+=cell.getFoodCount()+" food; ";
            }
            if(cell.isAnthill(Colour.RED)){
                msg+="red hill; ";
            }
            if(cell.isAnthill(Colour.BLACK)){
                msg+="black hill; ";
            }

            if(cell.findAnyMarker(Colour.RED)){
                msg+="red marks: " + getMarks(cell, Colour.RED) + "; ";
            }
            if(cell.findAnyMarker(Colour.BLACK)){
                msg+="black marks: "+ getMarks(cell, Colour.BLACK) + "; ";
            }
            if(cell.isOccupied()){
                msg+=getAntStatus(cell.getAnt());
            }
        }
        
        return msg;

    }
    
    //Scan an ant and format its state for outputting
    private String getAntStatus(Ant ant){
        
        String status = "";
        if(ant.getColour() == Colour.RED){
            status+="red ant of id ";
        }
        if(ant.getColour() == Colour.BLACK){
            status+="black ant of id ";
        }
        status+=ant.getId()-1 + ", ";
        status+="dir " + ant.getDirection() + ", ";
        if(ant.hasFood()){
            status+="food 1, ";
        }
        if(!ant.hasFood()){
            status+="food 0, ";
        }
        status+="state " + ant.getState() + ", ";
        status+="resting " + ant.getResting();
        
        return status;

        
        
    }
    
    //Scan cell markers and format their state for outputting
    private String getMarks(Cell cell, Colour colour){
        
        String marks = "";
        ArrayList<Integer> antMarks;
        if(colour == Colour.RED){
            antMarks = new ArrayList(cell.redMarkers);
        }
        else{
            antMarks = new ArrayList(cell.blackMarkers);
        }
        Collections.sort(antMarks);

        for (Integer antMark : antMarks) {
            marks+=antMark;
        }
        
        return marks;
        
        
    }
    
    //Dump the passed in string describing the round state to text file
    private void writeToFile(String round) {
        PrintWriter out = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter("dump-test.txt", true);
            bw = new BufferedWriter(fw);
            out = new PrintWriter(bw);
            out.println(round);
            out.close();
        } catch (FileNotFoundException ex) {
            System.out.println("something went wrong");
            out.close();
        } catch (IOException ex) {
            System.out.println("something went wrong");
            out.close();
        }
    }
    
}
