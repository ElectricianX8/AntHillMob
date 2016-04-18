/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

import instructions.Instruction;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import tokens.Token;

/**
 *
 * @author oliverthomas
 */
public class Lexer {

    public Lexer() {
    }

    public ArrayList<Token> lexAntBrain(String input) throws LexerException{
        try {
            ArrayList<Token> lexedInstructions = new ArrayList<>();
            
            GeneratedAntBrainLexer gabl = new GeneratedAntBrainLexer(new StringReader(input));
            Token nextResult = gabl.yylex();
            
            while(nextResult != null) {
                lexedInstructions.add(nextResult);
                nextResult = gabl.yylex();
            }
            return lexedInstructions;
        } catch (Exception | Error e) {
            throw new LexerException("Problem Lexing the AntBrain");
        }
    }
}
