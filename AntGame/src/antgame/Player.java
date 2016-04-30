package antgame;

import parsers.Parser;
import parsers.LexerException;
import parsers.Lexer;
import parsers.AntBrain;
import parsers.ParsingException;
import instructions.Instruction;
import instructions.NotValidInstructionException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Player class representing a player of the Ant Game.
 */
public class Player {

    private String name;
    private int id;
    private AntBrain brain;
    private int score;

    /**
     * Constructor for testing. Should be removed on submission.
     */
    public Player() {
    }

    /**
     * Constructor. Called once the player creation window has been called.
     *
     * @param name String of player's name
     * @param id ID number auto assigned, for use as primary key.
     * @param ab AntBrain for the player.
     */
    public Player(String name, int id, AntBrain ab){
        this.name = name;
        this.id = id;
        this.brain = ab;
        score = 0;
    }

    /**
     * Gets the player name.
     *
     * @return player name.
     */
    public String getName() {
        return name;
    }
    
    public void setScore(int score){
        this.score = score;
    }
    
    public int getScore(){
        return score;
    }

    /**
     * Set the Player Name
     *
     * @param name updated player name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the ID of the player.
     *
     * @return Player ID
     */
    public int getId() {
        return id;
    }

    /**
     * Set the ID of the player. ID should be unique and shouldn't change.
     * Method for testing only.
     *
     * @param id id num to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the AntBrain for the player.
     *
     * @return the antbrain.
     */
    public AntBrain getAntBrain() {
        return brain;
    }

    /**
     * Set the AntBrain to be a new one.
     *
     * @param ab The AntBrain to set.
     */
    public void setAntBrain(AntBrain ab) {
        brain = ab;
    }

    /**
     * Load an AntBrain from a file and set it to be the new AntBrain for the
     * player.
     *
     * @param f the file containing the brain
     * @throws java.io.IOException as reading from a file
     * @throws antgame.LexerException If error occurs during Lexing
     * @throws antgame.ParsingException if Error occurs during Parsing
     * @throws instructions.NotValidInstructionException If Instruction given is not valid
     */
    public void loadAntBrain(File f) throws IOException, LexerException, ParsingException, NotValidInstructionException {
        AntBrain ab;
        String content = readFile(f.getPath(), Charset.defaultCharset());

        Lexer lex = new Lexer();
        Parser parse = new Parser();
        ab = parse.parseAntBrain(lex.lexAntBrain(content));
        setAntBrain(ab);
    }

    /**
     * Reads a .txt file and returns the contents as a String
     * @param path File Path
     * @param encoding Charset encoding
     * @return File contents in a String
     * @throws IOException as reading from a file
     */
    public String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

    /**
     * Saves the AntBrain To File
     */
    public void saveAntBrain() {
        FileWriter fw;
        AntBrain toSave = this.brain;
        try {
            fw = new FileWriter(toSave.getName() + ".txt");
            fw.write("~"+toSave.getName());
            for (Instruction i : toSave.getInstructionArray()) {
                fw.write("\n"+i.toString());
            }
            fw.close();
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
