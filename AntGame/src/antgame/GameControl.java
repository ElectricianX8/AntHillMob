/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

import java.util.ArrayList;

/**
 * Interface to allow control from within the UI
 */
public interface GameControl {

    /**
     * Returns the list of players, sorted in descending order, first by score,
     * followed by name.
     *
     * @return The list of players sorted in score order.
     */
    public ArrayList<Player> getLeaderboard();

    /**
     * Setup a single round of a single game.
     *
     * @param players Players to play the game.
     * @param worlds World/worlds to play the game on.
     */
    public void setSingleGame(ArrayList<Player> players, ArrayList<GameBoard> worlds);

    /**
     * Setup a full tournament.
     *
     * @param players Players to play the tournament.
     * @param worlds Worlds to be played on within the tournament.
     */
    public void setTournament(ArrayList<Player> players, ArrayList<GameBoard> worlds);

    /**
     * Returs the next match to be played.
     *
     * @return Return the next match to be played, null if all matches are
     * finished.
     */
    public QueueMatch getNextMatch();

    /**
     * Run the next waiting match.
     *
     * @return Returns the result of the match.
     * @throws Exception Throw Exception if the match does not run properly.
     */
    public Result startNextMatch() throws Exception;

    /**
     * Set the initial time delay between ant moves.
     *
     * @param delay The delay to be set in miliseconds.
     */
    public void changeDelay(int delay);

    /**
     * Set the delay while the game is running.
     *
     * @param delay The delay to be set in miliseconds.
     */
    public void changeRunningDelay(int delay);

    /**
     * Returns the currently running game.
     *
     * @return Returns the currently running game.
     */
    public Game getCurrentGame();

    /**
     * Returns the result of the last match.
     *
     * @return The result of the last match.
     */
    public Result getLastResult();
}
