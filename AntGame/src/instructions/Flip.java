package instructions;

/**
 *
 * @author alisaleem
 */
public class Flip {
    private int n;
    private int nisOState;
    private int nGTOState;
    
    Flip(){
        n = 0;
        nisOState = 0;
        nGTOState = 0;
    }
    
    Flip(int n, int nisOState, int nGTOState){
        this.n = n;
        this.nisOState = nisOState;
        this.nGTOState = nGTOState;
    }

    /**
     * @return the n
     */
    public int getN() {
        return n;
    }

    /**
     * @param n the n to set
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * @return the nisOState
     */
    public int getNisOState() {
        return nisOState;
    }

    /**
     * @param nisOState the nisOState to set
     */
    public void setNisOState(int nisOState) {
        this.nisOState = nisOState;
    }

    /**
     * @return the nGTOState
     */
    public int getnGTOState() {
        return nGTOState;
    }

    /**
     * @param nGTOState the nGTOState to set
     */
    public void setnGTOState(int nGTOState) {
        this.nGTOState = nGTOState;
    }
    
    public String toString() {
        return "Flip";
    }
}
