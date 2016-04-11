package antgame;

import java.io.File;

/**
 *
 * @author alisaleem
 */
public class Player {

    private String name;
    private int id;
    private AntBrain brain;

    public Player(String name) {
        this.name = name;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the brain
     */
    public AntBrain getAntBrain() {
        return brain;
    }

    /**
     * @param f the file containing the brain
     */
    public void loadAntBrain(File f) {
        throw new UnsupportedOperationException("Brain can't be loaded yet.");
    }
    
    public AntBrain createNewBrain(){
        return new AntBrain();
    }

    /**
     * @param brain the brain to save
     */
    public void saveAntBrain(AntBrain brain) {
        throw new UnsupportedOperationException("Brain can't be saved yet.");
    }
}
