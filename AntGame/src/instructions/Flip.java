package instructions;

/**
 * Choose a random number from 0 to n-1.
 * Go to nis0State if n == 0,
 * else go to nisGT0State if n > 0.
 */
public class Flip extends Instruction {
    private int n;
    private int nis0State;
    private int nisGT0State;
    
    /**
     * Constructor for testing purposes.
     * Should be removed before submission.
     */
    public Flip(){
        n = 0;
        nis0State = 0;
        nisGT0State = 0;
    }
    
    /**
     * Constructor for the instruction/
     * @param n Max value of the random number generator.
     * @param nisOState state to transition to if random number is 0.
     * @param nGTOState state to transition to otherwise.
     */
    public Flip(int n, int nisOState, int nGTOState) throws NotValidInstructionException{
        if (n < 1) {
            throw new NotValidInstructionException("Max random number generated must be > 0");
        }
        this.n = n;
        this.nis0State = nisOState;
        this.nisGT0State = nGTOState;
    }

    /**
     * Get the max number for random number generation.
     * @return the max number for the random number generator.
     */
    public int getN() {
        return n;
    }

    /**
     * Set the max number for the random number generator (RNG).
     * @param n the max number for the random number generator.
     */
    public void setN(int n) {
        this.n = n;
    }

    /**
     * Get the state to transition to if RNG produces a 0.
     * @return the state to transition to if RNG produces a 0.
     */
    public int getNisOState() {
        return nis0State;
    }

    /**
     * Set the state to transition to if RNG produces a 0.
     * @param nisOState the state to transition to if the RNG produces a 0.
     */
    public void setNisOState(int nisOState) {
        this.nis0State = nisOState;
    }

    /**
     * Get the state to transition to if the RNG produces a number > 0.
     * @return the state to transition if the RNG produces a value > 0.
     */
    public int getnGTOState() {
        return nisGT0State;
    }

    /**
     * Set the state to transition to if the RNG produces a value > 0.
     * @param nGTOState the state to transition to if the RNG produces a value > 0.
     */
    public void setnGTOState(int nGTOState) {
        this.nisGT0State = nGTOState;
    }
    
    /**
     * String representation of the instruction.
     * @return String representation of the instruction.
     */
    @Override
    public String toString() {
        return "Flip "+this.n+" "+this.nis0State+" "+this.nisGT0State;
    }
}
