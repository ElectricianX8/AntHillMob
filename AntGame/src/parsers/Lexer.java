/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parsers;


import java.io.StringReader;
import java.util.ArrayList;
import tokens.Token;

/**
 *
 * The class used for lexing brains.
 */
public class Lexer {

    /**
     * Default constructor.
     */
    public Lexer() {
    }

    /**
     * Lex the brain structure.
     * @param input The brain to be lexed.
     * @return A list of tokens containing brain instructions.
     * @throws LexerException Invalid structure.
     */
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
            throw new LexerException(e.getMessage());
        }
    }
}
