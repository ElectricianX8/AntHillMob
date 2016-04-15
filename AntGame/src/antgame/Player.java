package antgame;

import instructions.Instruction;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * Player class representing a player of the Ant Game.
 */
public class Player {

    private String name;
    private int id;
    private AntBrain brain;

    /**
     * Constructor. Called once the player creation window has been called.
     * @param name String of player's name
     * @param id ID number auto assigned, for use as primary key.
     * @param ab AntBrain for the player.
     */
    public Player(String name, int id, AntBrain ab) {
        this.name = name;
        this.id = id;
        this.brain = ab;
    }

    /**
     * Gets the player name.
     * @return player name.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the Player Name
     * @param name updated player name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the ID of the player.
     * @return Player ID
     */
    public int getId() {
        return id;
    }

    /**
     * Set the ID of the player.
     * ID should be unique and shouldn't change. Method for testing only.
     * @param id id num to be set.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Get the AntBrain for the player.
     * @return the antbrain.
     */
    public AntBrain getAntBrain() {
        return brain;
    }
    
    /**
     * Set the AntBrain to be a new one.
     * @param ab The AntBrain to set.
     */
    public void setAntBrain(AntBrain ab) {
        brain = ab;
    }

    /**
     * Load an AntBrain from a file and set it to be the new AntBrain for the player.
     * @param f the file containing the brain
     * @throws java.io.IOException
     */
    public void loadAntBrain(File f) throws IOException {
        AntBrain ab;
        ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(f.toPath(), Charset.forName("UTF-8"));
        String[] arr = lines.toArray(new String[lines.size()]);
        Instruction[] instructionsForAntBrain;
        
        Lexer lex = new Lexer();
        Parser parse = new Parser();
        instructionsForAntBrain = parse.parseAntBrain(lex.lexAntBrain(arr));
        ab = new AntBrain(instructionsForAntBrain);
    }
}
