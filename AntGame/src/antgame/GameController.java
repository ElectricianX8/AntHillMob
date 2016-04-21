/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 *
 * Wrapper that manages games/tournaments
 */
public class GameController implements GameControl, Runnable {

    ArrayList<QueueMatch> waitingMatches;
    ArrayList<Player> playerList;
    ArrayList<GameBoard> worldList;
    Game currentGame;
    Result lastResult;
    int delay;

    public GameController() {

        waitingMatches = new ArrayList<QueueMatch>();
        delay = 0;
    }

    @Override
    public ArrayList<Player> getLeaderboard() {

        Collections.sort(playerList, new Comparator<Player>() {

            @Override
            public int compare(Player o1, Player o2) {
                if (o1.getScore() < o2.getScore()) {
                    return 1;
                } else if (o1.getScore() == o2.getScore()) {
                    return o1.getName().compareTo(o2.getName());
                } else {
                    return -1;
                }
            }
        });

        return playerList;
    }

    @Override
    public void setSingleGame(ArrayList<Player> players, ArrayList<GameBoard> worlds) {

        QueueMatch match = new QueueMatch(players.get(0), players.get(1), worlds.get(0));
        waitingMatches.add(match);
        
        for(int i = 0; i<waitingMatches.size();i++){
            System.out.println(waitingMatches.get(i));
        }
    }

    @Override
    public void setTournament(ArrayList<Player> players, ArrayList<GameBoard> worlds) {

        playerList = players;
        worldList = worlds;

        for (int i = 0; i < playerList.size() - 1; i++) {
            for (int j = i + 1; j < playerList.size(); j++) {
                for (int w = 0; w < worldList.size(); w++) {
                    QueueMatch matchHome = new QueueMatch(playerList.get(i), playerList.get(j), worldList.get(w));
                    QueueMatch matchAway = new QueueMatch(playerList.get(j), playerList.get(i), worldList.get(w));
                    waitingMatches.add(matchHome);
                    waitingMatches.add(matchAway);
                }
            }
        }
        
        for(int i = 0; i<waitingMatches.size();i++){
            System.out.println(waitingMatches.get(i));
        }
        
    }

    @Override
    public QueueMatch getNextMatch() {

        if (waitingMatches.size() > 0) {
            return waitingMatches.get(0);
        } else {
            return null;
        }

    }

    @Override
    public Result startNextMatch() throws Exception {

        QueueMatch match = getNextMatch();
        currentGame = new Game(match.getRedPlayer(), match.getBlackPlayer(), copy(match.getGameBoard()));
        currentGame.changeDelay(delay);

        Result result = currentGame.start();
        Player p1 = result.getWinner();
        Player p2 = result.getLoser();
        if (result.gameWasATie()) {

            p1.setScore(p1.getScore() + 1);
            p2.setScore(p2.getScore() + 1);
        } else {
            p1.setScore(p1.getScore() + 2);
        }
        waitingMatches.remove(0);
        currentGame = null;
        return result;

    }

    @Override
    public void changeDelay(int delay) {
        this.delay = delay;
    }

    public void changeRunningDelay(int delay) {
        currentGame.changeDelay(delay);
    }

    private GameBoard copy(GameBoard gameBoard) {

        int height = gameBoard.getHexGrid().length;
        int width = gameBoard.getHexGrid()[0].length;
        Cell[][] newBoard = new Cell[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Cell oldCell = gameBoard.getHexGrid()[i][j];

                Cell newCell = new Cell(oldCell.getTerrain(), oldCell.getAntHillColour());
                if (oldCell.getFoodCount() > 0) {
                    newCell.setFoodCount(oldCell.getFoodCount());
                }

                newBoard[i][j] = newCell;
            }
        }

        return new GameBoard(newBoard, gameBoard.getAnthills());
    }

    public Game getCurrentGame() {
        //System.out.println("Current Game: " + currentGame);
        return currentGame;
    }

    @Override
    public void run() {
        try {
            lastResult = startNextMatch();
        } catch (Exception ex) {
            System.out.println("oops, gameController crashed");
        }
    }

    public Result getLastResult() {
        return lastResult;
    }

}
