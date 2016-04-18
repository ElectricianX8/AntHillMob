package antgame;

/**
 *
 */
public class Result {
    private static int matchNumberCount = 1;
    private final int matchNum;
    private Player winner;
    private Player loser;
    private boolean tie;
    
    public Result(Player w, Player l, boolean tie){
        matchNum = matchNumberCount++;
        winner = w;
        loser = l;
        this.tie = tie;
    }
    

    /**
     * Get the match number of the result
     * @return game number of the result
     */
    public int getMatchNum() {
        return matchNum;
    }

    /**
     * Get the Winning player of the match
     * @return match winner
     */
    public Player getWinner() {
        return winner;
    }

    /**
     * Get the losing Player of the Match
     * @return match loser
     */
    public Player getLoser() {
        return loser;
    }
    
    public boolean gameWasATie(){
        return tie;
    }
    
}
